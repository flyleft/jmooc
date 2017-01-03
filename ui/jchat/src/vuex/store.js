import Vue from 'vue';
import Vuex from 'vuex';
import * as actions from './actions'
import mutations from './mutations';

Vue.use(Vuex);

const now = new Date();
const state = {
  // 当前用户
  user: {
      name: 'coffce',
      img: '/static/user.jpg'
  },
  // 会话列表
  sessions: [
      {
          id: 1,
          user: {
              name: '示例介绍',
              img: '/static/user.jpg'
          },
          messages: [
              {
                  content: 'Hello，这是一个基于Vue + Vuex + Webpack构建的简单chat示例，聊天记录保存在localStorge, 有什么问题可以通过Github Issue问我。',
                  date: now
              }, {
                  content: '项目地址: https://github.com/coffcer/vue-chat',
                  date: now
              }
          ]
      },
      {
          id: 2,
          user: {
              name: 'webpack',
              img: '/static/user.jpg'
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
    mutations
})

export default store
