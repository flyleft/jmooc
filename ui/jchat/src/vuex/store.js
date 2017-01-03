import Vue from 'vue';
import Vuex from 'vuex';
import * as actions from './actions'
import * as getters from './getters'
import mutations from './mutations';

Vue.use(Vuex);

const now = new Date();
const state = {
  // 当前用户
  user: {
      name: 'coffce',
      img: '/static/jcala.jpg'
  },
  // 会话列表
  sessions: [
      {
          id: 1,
          user: {
              name: 'kaxi',
              img: '/static/kaxi.jpg'
          },
          messages: [
              {
                  content: 'Hello,好久不见',
                  date: now
              }, {
                  content: '你还好吗？',
                  date: now
              }
          ]
      },
      {
          id: 2,
          user: {
              name: 'paoying',
              img: '/static/paoying.jpg'
          },
          messages: []
      }
  ],
  // 当前选中的会话
  currentSessionId: 1,
  // 过滤出只包含这个key的会话
  filterKey: ''
}
const store = new Vuex.Store({
    state,
    actions,
    getters,
    mutations
})

export default store
