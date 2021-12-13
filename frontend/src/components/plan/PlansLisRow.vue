<template>
  <div>
    <template v-if="plan.planPointsDto !== undefined && plan.planPointsDto.length > 0">
      <div
          class="row my-0"
          @click="selectItem"
          v-for="pp of orderedPoints"
          :key="pp.id"
      >
        <div class="col-1 border-top my-1">
          {{ pp.orderInPlan }}
        </div>
        <div class="col-sm border-top my-1">
          {{ pp.projectDesignation }}
        </div>
        <div class="col-sm border-top my-1">
          {{ pp.projectName }}
        </div>

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