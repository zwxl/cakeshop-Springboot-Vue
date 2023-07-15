<template>
   <div>
     <Nav/>
     <router-view></router-view>
   </div>
</template>

<script>
import Nav from "@/userview/Nav";
export default {
  name: "Layout",
  components:{
    Nav
  }
}
</script>

<style scoped>

</style>