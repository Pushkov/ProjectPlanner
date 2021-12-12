<template>
  <div>
    <template v-if="plan.planPointsDto !== undefined && plan.planPointsDto.length > 0">
      <div @click="selectItem"
           v-for="pp of orderedPoints"
           :key="pp.id"
      >
        {{ pp.orderInPlan }} - {{ pp.projectDesignation }}-{{ pp.projectName }}
      </div>
    </template>
    <template v-else-if="plan.planPointsDto !== undefined && plan.planPointsDto.length === 0">
      <div @click="selectItem">
        {{ $t('plan.empty_plan') }}
      </div>
      <div>

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
  },
  computed: {
    orderedPoints() {
      let items = this.plan.planPointsDto;
      return items.filter(item => {
        if (item['orderInPlan']) return item;
      });
    }
  },
  methods: {
    selectItem() {
      this.$emit('selectItem', this.plan)
    }
  }
}
</script>

<style scoped>

</style>