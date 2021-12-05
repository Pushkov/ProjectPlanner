<template>
  <div>
    {{ plan.year }}-{{ plan.month }}-{{ plan.departmentId }}-{{ plan.departmentName }}
    <template v-if="plan.planPointsDto !== undefined && plan.planPointsDto.length > 0">
      <div v-for="pp of plan.planPointsDto"
           :key="pp.id"
      >
        {{ pp.id }}-{{ pp.year }}-{{ pp.month }}-{{ pp.departmentName }}-{{ pp.projectDesignation }}-{{ pp.projectName }}
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