<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>
*{
  margin: 0;
  padding: 0;
}
html{
  /* overflow: hidden; */
  height: 100%;
}
body{
  height: 100%;
}
a{
  text-decoration:none;
}
#app{
  height: 100%;
  /* filter: grayscale(100%); */
}
</style>
