import Vue from 'vue'
import store from './vuex/store'
import App from './App'

new Vue({
  el: '#chat',
  store,
  render: h=> h(App)
})
