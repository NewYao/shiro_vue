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
.el-header .el-select,.form_input ,.el-button  ,.el-date-editor{
    margin: 5px 5px 0px 0;
    margin-left: 0!important;
}
.el-header .el-select{
  width: 150px;
}
table .el-button--mini {
    padding: 3px 4px;
    margin: 2px;
}
</style>
