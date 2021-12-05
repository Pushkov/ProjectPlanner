<template>
  <div>
    <NavigationBar/>
    <PlansList/>
  </div>
</template>

<script>
import NavigationBar from "@/components/nav/NavigationBar";
import PlansList from "@/components/plan/PlansList";

export default {
  name: "Index",
  components: {
    PlansList,
    NavigationBar
  }
}
</script>

<style scoped>

</style>