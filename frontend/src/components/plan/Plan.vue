<template class="mt-xl-5">
  <div class="  w-75 m-auto py-3">
    <div class="row m-auto">
      <b-button
          @click="goToPlansList"
      ><h5 class="m-auto">{{ $t("button.back") }}</h5>
      </b-button>
    </div>
    <div v-if="PLAN !== undefined">
      <h4 class="my-3">
        План {{ PLAN.departmentName }} на {{ PLAN.month }} месяц {{ PLAN.year }} года
      </h4>
      <div class="row m-auto">
        <b-button
            class="col-auto m-2"
            @click="addPoint"
        ><h5 class="m-auto">{{ $t("plan.add_point") }}</h5>
        </b-button>
        <div class="col-sm">

        </div>
        <a v-if="PLAN.planPointsDto !== undefined && PLAN.planPointsDto.length > 0" :href="getHref" download
        >
          <b-button
              class="col-auto m-2"
          ><h5 class="m-auto">{{ $t("plan.to_pdf") }}</h5>
          </b-button>
        </a>

      </div>
      <!--*******************************-->
      <table class="m-auto table table-hover table-bordered table-striped">
        <thead>
        <tr>
          <th colspan="4">{{ $t('plan.order') }}</th>
          <th>{{ $t('plan.project_designation') }}</th>
          <th>{{ $t('plan.project_name') }}</th>
          <th>{{ $t('plan.designer') }}</th>
          <th>{{ $t('plan.date_start') }}</th>
          <th>{{ $t('plan.date_end') }}</th>
          <th>{{ $t('plan.result') }}</th>
        </tr>
        </thead>
        <tbody>
        <template v-if="PLAN.planPointsDto !== undefined && PLAN.planPointsDto.length > 0">
          <tr v-for="pp of orderedPoints"
              :key="pp.id"
          >
            <td class="px-0">
              <minus-icon
                  @click="removePoint(pp)"
                  fill-color="red"
              />
            </td>
            <td class="px-0">
              <arrow-up-icon
                  @click="lineUp"
              />
            </td>
            <td class="px-0">
              <arrow-down-icon
                  @click="lineDown"
              />
            </td>
            <td class="px-3">
              {{ pp.orderInPlan }}
            </td>
            <td @click="editPlanPoint(pp)">{{ pp.projectDesignation }}</td>
            <td @click="editPlanPoint(pp)">{{ pp.projectName }}</td>
            <td @click="editPlanPoint(pp)">{{ pp.designerName }}</td>
            <td @click="editPlanPoint(pp)">{{ pp.startDate }}</td>
            <td @click="editPlanPoint(pp)">{{ pp.endDate }}</td>
            <td @click="editPlanPoint(pp)">{{ pp.readiness }}</td>
          </tr>
        </template>
        <template v-else>
          <tr>
            <td colspan="100%">{{ $t('plan.empty_plan') }}</td>
          </tr>
        </template>
        </tbody>
      </table>


      <!--*******************************-->
    </div>
    <div v-else>
      {{ $t('plan.empty_plan') }}
    </div>
  </div>
</template>

<script>
import {mapActions, mapGetters} from "vuex";
import router from "@/router";

export default {
  name: "Plan",
  data() {
    return {
      year: 2020,
      month: 1,
      department_id: 1
    }
  },
  computed: {
    ...mapGetters([
      'PLAN'
    ]),
    getYear() {
      return this.$route.params.year;
    },
    getMonth() {
      return this.$route.params.month;
    },
    getDepartmentId() {
      return this.$route.params.department_id;
    },
    orderedPoints() {
      if (this.PLAN.planPointsDto !== undefined) {
        let items = this.PLAN.planPointsDto;
        return items.sort((a, b) => {
          return a.orderInPlan > b.orderInPlan
        });
      }
      return []
    },
    getHref() {
      const year = this.getYear;
      const month = this.getMonth;
      const depId = this.getDepartmentId;
      return 'http://localhost:8080/planner/plans/' + year + '/' + month + '/' + depId + '/export/pdf'
    }

  },
  methods: {
    ...mapActions([
      'GET_PLAN',
      'GET_PLAN_POINT',
      'DELETE_PLAN_POINT',
    ]),
    goToPlansList() {
      router.push('/planner/plans')
    },
    lineUp() {

    },
    lineDown() {

    },
    addPoint() {
      const year = this.getYear;
      const month = this.getMonth;
      const depId = this.getDepartmentId;
      router.push('/planner/plans/' + year + '/' + month + '/' + depId + '/0');
    },
    removePoint(value) {
      const plan = {
        'year': this.getYear,
        'month': this.getMonth,
        'department_id': this.getDepartmentId,
      }
      this.DELETE_PLAN_POINT(value.id).then(() => {
        this.GET_PLAN(plan);
      });

    },
    editPlanPoint(item) {
      const year = this.getYear;
      const month = this.getMonth;
      const depId = this.getDepartmentId;
      router.push('/planner/plans/' + year + '/' + month + '/' + depId + '/' + item.id);
    },

  },
  mounted() {
    const plan = {
      'year': this.getYear,
      'month': this.getMonth,
      'department_id': this.getDepartmentId,
    }
    this.GET_PLAN(plan);
  }
}
</script>

<style scoped>

</style>