<template>
  <div>
    <NavigationBar/>
    <PlanPoint/>
  </div>
</template>

<script>
import NavigationBar from "@/components/nav/NavigationBar";
import PlanPoint from "@/components/plan/PlanPoint";

export default {
  name: "PlanPointPage",
  components: {
    PlanPoint,
    NavigationBar
  },
}
</script>

<style scoped>

</style>