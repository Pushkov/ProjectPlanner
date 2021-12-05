<template>
  <div>
    <NavigationBar/>
    <ProjectsList/>
  </div>
</template>

<script>
import NavigationBar from "@/components/nav/NavigationBar";
import ProjectsList from "@/components/project/ProjectsList";

export default {
  name: "Index",
  components: {
    ProjectsList,
    NavigationBar
  }
}
</script>

<style scoped>

</style>