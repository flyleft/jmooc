<script>
import Chat from './component/ChatList.vue';
export default {
  name: 'app',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App'
    }
  },
  components: {
    Chat
  }
}
</script>

<template>
    <div id="chat">
        <Chat/>
    </div>
</template>


<style>

</style>