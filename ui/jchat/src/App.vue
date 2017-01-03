<template>
  <div id="chat">
    <div class="sidebar">
      <card></card>
      <list></list>
    </div>
    <div class="main">
      <message></message>
      <input-text></input-text>
    </div>
  </div>
</template>

<script>
import Card from './components/Card';
import InputText from './components/InputText';
import Message from './components/Message';
import List from './components/UserList';

export default {
  name: 'chat',
  components: {
    Card,InputText,Message,List
  },
  created () {
        this.$store.dispatch('initData');
  }
}
</script>

<style lang="less" scoped>
#chat {
    margin: 20px auto;
    width: 800px;
    height: 600px;

    overflow: hidden;
    border-radius: 3px;

    .sidebar, .main {
        height: 100%;
    }
    .sidebar {
        float: left;
        width: 200px;
        color: #f4f4f4;
        background-color: #2e3238;
    }
    .main {
        position: relative;
        overflow: hidden;
        background-color: #eee;
    }
    .text {
        position: absolute;
        width: 100%;
        bottom: 0;
        left: 0;
    }
    .message {
        height: ~'calc(100% - 160px)';
    }
}
</style>
