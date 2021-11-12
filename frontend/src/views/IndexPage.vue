<template>
  <div>
    <NavigationBar/>
    <MainSearch/>
  </div>
</template>

<script>
import NavigationBar from "@/components/nav/NavigationBar";
import MainSearch from "@/components/MainSearch";

export default {
  name: "Index",
  components: {
    MainSearch,
    NavigationBar
  }
}
</script>

<style scoped>

</style>