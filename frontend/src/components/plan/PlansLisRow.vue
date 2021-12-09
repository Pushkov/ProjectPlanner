<template>
  <div>
    <template v-if="plan.planPointsDto !== undefined && plan.planPointsDto.length > 0">
      <div v-for="(pp,index) of plan.planPointsDto"
           :key="pp.id"
      >
        {{ index + 1 }} - {{ pp.projectDesignation }}-{{ pp.projectName }}
      </div>
    </template>
  </div>
</template>

<script>
export default {
  name: "PlansLisRow",
  props: {
    plan: {
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