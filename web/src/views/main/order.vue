<template>
 <div calss="order-train">
  <span class="order-train-main">{{dailyTrainTicket.date}}</span>&nbsp;
  <span class="order-train-main">{{dailyTrainTicket.trainCode}}</span>次&nbsp;
  <span class="order-train-main">{{dailyTrainTicket.start}}</span>站
  <span class="order-train-main">（{{dailyTrainTicket.startTime}}）</span>&nbsp;
  <span class="order-train-main">——</span>&nbsp;
  <span class="order-train-main">{{dailyTrainTicket.end}}</span>站
  <span class="order-train-main">（{{dailyTrainTicket.endTime}}）</span>&nbsp;
  <div class="order-train-ticket">
     <span v-for = "item in seatTypes" :key="item.type">
      <span>{{item.desc}}</span>:
      <span class="order-train-ticket-main"> {{item.price}}¥</span>&nbsp;
      <span class="order-train-ticket-main">{{item.count}}</span>&nbsp;张票&nbsp;&nbsp;
     </span>

  </div>
 </div>
 <br/>
 <a-drivder></a-drivder>
 <b>勾选要选购的乘客：</b> &nbsp;
  <a-checkbox-group v-model:value="passengerChecks" :options="passengerOptions" />
 <br/>
    <div class="order-tickets">
        <a-row class="order-tickets-header" v-if="tickets.length > 0">
            <a-col :span="2">乘客</a-col>
            <a-col :span="6">身份证</a-col>
            <a-col :span="4">票种</a-col>
            <a-col :span="4">座位类型</a-col>
        </a-row>
        <a-row class="order-tickets-row" v-for="ticket in tickets" :key="ticket.passengerId">
            <a-col :span="2">{{ticket.passengerName}}</a-col>
            <a-col :span="6">{{ticket.passengerIdCard}}</a-col>
            <a-col :span="4">
                <a-select v-model:value="ticket.passengerType" style="width: 100%">
                    <a-select-option v-for="item in PASSENGER_TYPE_ARRAY" :key="item.code" :value="item.code">
                        {{item.desc}}
                    </a-select-option>
                </a-select>
            </a-col>
            <a-col :span="4">
                <a-select v-model:value="ticket.seatTypeCode" style="width: 100%">
                    <a-select-option v-for="item in seatTypes" :key="item.code" :value="item.code">
                        {{item.desc}}
                    </a-select-option>
                </a-select>
            </a-col>
        </a-row>
    </div>
  <div v-if="tickets.length > 0">
      <a-button type="primary" size="large" @click="finishCheckPassenger">提交订单</a-button>
  </div>

  <a-modal v-model:visible="visible" title="请核对以下信息"
           style="top: 50px; width: 800px"
           ok-text="确认" cancel-text="取消"
           @ok="showFirstImageCodeModal">
    <div class="order-tickets">
      <a-row class="order-tickets-header" v-if="tickets.length > 0">
        <a-col :span="3">乘客</a-col>
        <a-col :span="15">身份证</a-col>
        <a-col :span="3">票种</a-col>
        <a-col :span="3">座位类型</a-col>
      </a-row>
      <a-row class="order-tickets-row" v-for="ticket in tickets" :key="ticket.passengerId">
        <a-col :span="3">{{ticket.passengerName}}</a-col>
        <a-col :span="15">{{ticket.passengerIdCard}}</a-col>
        <a-col :span="3">
          <span v-for="item in PASSENGER_TYPE_ARRAY" :key="item.code">
            <span v-if="item.code === ticket.passengerType">
              {{item.desc}}
            </span>
          </span>
        </a-col>
        <a-col :span="3">
          <span v-for="item in seatTypes" :key="item.code">
            <span v-if="item.code === ticket.seatTypeCode">
              {{item.desc}}
            </span>
          </span>
        </a-col>
      </a-row>
    </div>
  </a-modal>

</template>

<script>
import axios from "axios";
import {notification} from "ant-design-vue";
import {onMounted, ref, watch} from "vue";

export default {
    name: "order-view",
    setup(){
        const dailyTrainTicket = SessionStorage.get(SESSION_ORDER) || {};
        console.log("下单的车次信息", dailyTrainTicket);
        const passengers = ref([]);
        const passengerOptions = ref([]);
        const passengerChecks = ref([]);
        const tickets = ref([]);
        const PASSENGER_TYPE_ARRAY = window.PASSENGER_TYPE_ARRAY;
        const SEAT_TYPE = window.SEAT_TYPE;
        const visible = ref(false);
        console.log(SEAT_TYPE);
        const seatTypes = [];
        for( let KEY in SEAT_TYPE) {
           let key = KEY.toLowerCase();
           if(dailyTrainTicket[key] >=0){
            seatTypes.push({
             type: KEY,
             code: SEAT_TYPE[KEY]["code"],
             desc: SEAT_TYPE[KEY]["desc"],
             count: dailyTrainTicket[key],
             price: dailyTrainTicket[key + 'Price'],
            })
           }
        }
        console.log("本车次提供的座位:", seatTypes);
        const hanleQueryPassenger = () => {
            axios.get("/member/passenger/query-mine").then((response) => {
             let data = response.data;
             if(data.success){
              passengers.value = data.content;
              passengers.value.forEach((item) => passengerOptions.value.push({
                 label: item.name,
                 value: item,
              }))
             }else{
              notification.error({description: data.message});
             }
            })
        };
        watch(() => passengerChecks.value, (newVal, oldVal) => {
         console.log("勾选乘客发生变化",newVal,oldVal)
         //每次有变化时，把购票列表清空，重新构造列表
         tickets.value = [];
         passengerChecks.value.forEach((item) => tickets.value.push({
          passengerId: item.id,
          passengerType: item.type,
          seatTypeCode: seatTypes[0].code,
          passengerName: item.name,
          passengerIdCard: item.idCard
         }))
        }, {immediate: true});
        onMounted(()=>{
          hanleQueryPassenger();
        });
    const finishCheckPassenger = () => {
        console.log("购票列表：", tickets.value);
        if(tickets.value.length > 5){
            notification.error({description: '最多只能购买5张车票'});
            return;
        }
        //弹出确认界面
        visible.value = true;
    }
      console.log("前端余票校验通过");

        return {
            dailyTrainTicket,
           seatTypes,
         passengers,
         hanleQueryPassenger,
         passengerOptions,
         passengerChecks,
         tickets,
            PASSENGER_TYPE_ARRAY,
            visible,
            finishCheckPassenger,
        };
    }
}
</script>

<style>
.order-train-main {
    font-size: 18px;
    font-weight: bold;
}
.order-train .order-train-ticket {
    margin-top: 15px;
}
.order-train-ticket .order-train-ticket-main {
    color: red;
    font-size: 18px;
}

.order-tickets {
    margin: 10px 0;
}
.order-tickets .ant-col {
    padding: 5px 10px;
}
.order-tickets .order-tickets-header {
    background-color: cornflowerblue;
    border: solid 1px cornflowerblue;
    color: white;
    font-size: 16px;
    padding: 5px 0;
}
.order-tickets .order-tickets-row {
    border: solid 1px cornflowerblue;
    border-top: none;
    vertical-align: middle;
    line-height: 30px;
}

.order-tickets .choose-seat-item {
    margin: 5px 5px;
}
</style>