<template>
  <div>
    <NavigationBar/>
    <HelloWorld msg="Welcome to Nicomed`s training App"/>
  </div>
</template>

<script>
import HelloWorld from '@/components/HelloWorld.vue'
import NavigationBar from "@/components/nav/NavigationBar";

export default {
  name: "Index",
  components: {
    NavigationBar,
    HelloWorld
  }
}
</script>

<style scoped>

</style>