<template>
  <div id="chat">
    <Card></Card>
  </div>
</template>

<script>
import Card from './components/Card';

export default {
  name: 'chat',
  components: {
    Card
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
