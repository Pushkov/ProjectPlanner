<template>
  <div>
    <NavigationBar/>
    <template v-if="this.$route.fullPath === '/planner/projects'">
      <ProjectsList/>
    </template>
    <template v-else>
      <Project/>
    </template>
  </div>
</template>

<script>
  import NavigationBar from "@/components/nav/NavigationBar";
  import ProjectsList from "@/components/project/ProjectsList";
  import Project from "../components/project/Project";

  export default {
    name: "Index",
    components: {
      Project,
      ProjectsList,
      NavigationBar
    }
  }
</script>

<style scoped>

</style>