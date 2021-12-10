<template>
  <div>
    <div
        class="row w-75 m-auto"
    >
      <PlanSelectors
          ref="planSelector"
          :months="getMonthes"
          :years="OVERVIEW_YEARS"
          :departments="OVERVIEW_DEP_NAMES"
      />
    </div>

    <template v-if="PLANS !== undefined && PLANS.length > 0">
      <div
          class="row my-4"
          v-for="(plan, index) of PLANS"
          :key="index"
          :plan="plan">
        <table class=" w-75 m-auto table table-hover table-bordered table-striped">
          <thead class="thead-light">
          <tr>
            <th colspan="100%" class="m-0 p-1 row">
              <span class="col-1 m-xs-1">{{ $t('overview.year') }}:</span>
              <span class="col-1 m-xs-1 my-auto text-secondary border rounded-lg">{{ plan.year }}</span>
              <span class="col-1  m-xs-1">{{ $t('overview.month') }}:</span>
              <span class="col-1 m-xs-1 my-auto text-secondary border rounded-lg">{{ plan.month }}</span>
              <span class="col-1 m-xs-1">{{ $t('overview.department') }}:</span>
              <span class="col-1 m-xs-1 my-auto text-secondary border rounded-lg">{{ plan.departmentName }}</span>
            </th>
          </tr>
          </thead>
          <tbody>
          <tr>
            <td colspan="100%">
              <PlansLisRow
                      @selectItem='selectPlan'
                      :plan="plan"
              />
            </td>
          </tr>
          </tbody>
        </table>
      </div>


    </template>
    <template v-else>
      ! PLANS LIST IS EMPTY
    </template>
  </div>
</template>

<script>
  import {mapActions, mapGetters} from "vuex";
  import PlansLisRow from "./PlansLisRow";
  import PlanSelectors from "@/components/plan/PlanSelectors";

  export default {
    name: "PlansList",
    components: {PlanSelectors, PlansLisRow},

    computed: {
      ...mapGetters([
        'PLANS',
        'OVERVIEW_YEARS',
        'OVERVIEW_MONTHS',
        'OVERVIEW_DEP_NAMES',
        'ALL_LOCALE_MONTHS',
    ]),
    getMonthes() {
      let result = [];
      for (let i = 0; i < this.ALL_LOCALE_MONTHS.length; i++) {
        for (let j = 0; j < this.OVERVIEW_MONTHS.length; j++) {
          if (this.ALL_LOCALE_MONTHS[i].value === this.OVERVIEW_MONTHS[j]) {
            result.push(this.ALL_LOCALE_MONTHS[i]);
          }
        }
      }
      return result;
    },
  },
  methods: {
    ...mapActions([
      'GET_ALL_PLANS',
      'GET_OVERVIEW_YEARS',
      'GET_OVERVIEW_MONTHS',
      'GET_OVERVIEW_DEP_NAMES',

    ]),
    initSelectorsData() {
      this.GET_OVERVIEW_YEARS();
      this.GET_OVERVIEW_MONTHS();
      this.GET_OVERVIEW_DEP_NAMES();
    },
    selectPlan(item) {
      console.log("item " + item);
    }
  },
  mounted() {
    this.initSelectorsData();
    this.$refs.planSelector.searchItem();
  }
}
</script>

<style scoped>

</style>