<template>
  <div>
    <NavigationBar/>
    <TitleListMain/>
  </div>
</template>

<script>
  import NavigationBar from "@/components/nav/NavigationBar";
  import TitleListMain from "@/components/title/TitleListMain";

  export default {
    name: "Index",
    components: {
      TitleListMain,
      NavigationBar
    }
  }
</script>

<style scoped>

</style>