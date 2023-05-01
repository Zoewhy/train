import { createStore } from 'vuex'


const MEMBER = "MEMBER";

export default createStore({
  state: {
    member: JSON.parse(window.sessionStorage.getItem(MEMBER)) || {} //一种很常见写法避免空指针写法
  },
  getters: {
  },
  mutations: {
    setMember(state, _member){
        state.member = _member;
        window.sessionStorage.setItem(MEMBER,JSON.stringify(_member));
    }
  },
  actions: {
  },
  modules: {
  }
})
