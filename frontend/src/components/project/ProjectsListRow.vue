<template>
  <div>
    {{ project.designation }} - {{ project.name }}
  </div>
</template>

<script>
export default {
  name: "ProjectsListRow",
  props: {
    project: {
      type: Object,
      default() {
        return {}
      }
    }
  }
}
</script>

<style scoped>

</style>