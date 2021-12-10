<template>
  <div>
    <template v-if="plan.planPointsDto !== undefined && plan.planPointsDto.length > 0">
        <div @click="selectItem"
             v-for="(pp,index) of plan.planPointsDto"
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