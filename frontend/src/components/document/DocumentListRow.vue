<template>
  <div>
    <div v-if="document !== undefined && document !== {}">
      {{ document.name }}
    </div>
  </div>
</template>

<script>
export default {
  name: "DocumentListRow",
  props: {
    document: {
      type: Object,
      default() {
        return {}
      }
    }
  },
}
</script>

<style scoped>

</style>