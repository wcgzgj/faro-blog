import { createStore } from 'vuex'

const store = createStore({
  state: {
    user: {}
  },
  mutations: {
    setUser(state,user) {
      state.user=user;
    }
  },
  actions: {
  },
  modules: {
  }
})

//最后导出 store，可以在外部被使用
export default store;