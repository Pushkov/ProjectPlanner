<template>
  <div>
    <NavigationBar/>
    <PlansList v-if="isList"/>
    <Plan v-else-if="!isList"/>
  </div>
</template>

<script>
import NavigationBar from "@/components/nav/NavigationBar";
import PlansList from "@/components/plan/PlansList";
import Plan from "@/components/plan/Plan";

export default {
  name: "Index",
  components: {
    Plan,
    PlansList,
    NavigationBar
  },
  computed: {
    isList() {
      return this.$route.fullPath === '/planner/plans'
    }
  }
}
</script>

<style scoped>

</style>