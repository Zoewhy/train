import { createStore } from 'vuex'


const MEMBER = "MEMBER";

export default createStore({
  state: {
    member: window.SessionStorage.get(MEMBER) || {} //一种很常见写法避免空指针写法
  },
  getters: {
  },
  mutations: {
    setMember(state, _member){
        state.member = _member;
        window.SessionStorage.set(MEMBER, _member);
    }
  },
  actions: {
  },
  modules: {
  }
})
