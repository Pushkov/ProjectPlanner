<template>
  <div>
    <template v-if="PLANS !== undefined && PLANS.length > 0">
      <PlansLisRow
          v-for="(plan, index) of PLANS"
          :key="index"
          :plan="plan"
      />


    </template>
    <template v-else>
      ! PLANS LIST IS EMPTY
    </template>
  </div>
</template>

<script>
import {mapActions, mapGetters} from "vuex";
import PlansLisRow from "@/components/plan/PlansLisRow";

export default {
  name: "PlansList",
  components: {PlansLisRow},
  computed: {
    ...mapGetters([
      'PLANS'
    ]),
  },
  methods: {
    ...mapActions([
      'GET_ALL_PLANS'
    ]),
  },
  mounted() {
    this.GET_ALL_PLANS();
  }
}
</script>

<style scoped>

</style>