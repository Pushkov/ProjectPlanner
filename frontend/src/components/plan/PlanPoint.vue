<template class="mt-xl-5">
  <div class="  w-75 m-auto py-3">
    <div class="row m-auto">
      <b-button
          @click="goBackToPlan"
      ><h5 class="m-auto">{{ $t("button.back") }}</h5>
      </b-button>
    </div>
    PlanPoint id={{ getId }}
    project - {{ PLAN_POINT.projectDesignation }}


    <div class="row my-2">
      <div class="col-4 m-auto mr-2">
        {{ $t('plan.project') }}
      </div>
      <b-form-input
          v-if="!isEdit"
          id="input-login"
          class="col-sm text-secondary border rounded-lg m-0"
          readonly
          :value="currentPoint.projectDesignation"
          aria-describedby="input-login-help input-login-feedback"
          trim
      />
      <b-form-select
          v-else-if="isEdit"
          v-model="currentPoint.project"
          :options="PROJECTS"
          class="col-sm text-secondary border rounded-lg m-0"
          value-field="id"
          text-field="designation"
          disabled-field="notEnabled"
      />
    </div>
    <div class="row my-2">
      <div class="col-4 m-auto mr-2 ">
        {{ $t('plan.designer') }}
      </div>
      <b-form-input
          v-if="!isEdit"
          id="input-login"
          class="col-sm text-secondary border rounded-lg m-0"
          readonly
          :value="currentPoint.designerName"
          aria-describedby="input-login-help input-login-feedback"
          trim
      />
      <b-form-select
          v-else-if="isEdit"
          v-model="currentPoint.designer"
          :options="ENGINEERS"
          class="col-sm text-secondary border rounded-lg m-0"
          value-field="id"
          text-field="lastName"
          disabled-field="notEnabled"
      />
    </div>
    <div class="row my-2">
      <div class="col-4 m-auto mr-2 ">
        {{ $t('plan.date_start') }}
      </div>
      <b-form-input
          id="input-login"
          class="col-sm text-secondary border rounded-lg m-0"
          :readonly="!isEdit"
          :value="currentPoint.startDate"
          v-model="currentPoint.startDate"
          aria-describedby="input-login-help input-login-feedback"
          trim
      />
    </div>
    <div class="row my-2">
      <div class="col-4 m-auto mr-2 ">
        {{ $t('plan.date_end') }}
      </div>
      <b-form-input
          id="input-login"
          class="col-sm text-secondary border rounded-lg m-0"
          :readonly="!isEdit"
          :value="currentPoint.endDate"
          v-model="currentPoint.endDate"
          aria-describedby="input-login-help input-login-feedback"
          trim
      />
    </div>
    <div class="row my-2">
      <div class="col-4 m-auto mr-2 ">
        {{ $t('plan.result') }} ,%
      </div>
      <b-form-input
          id="input-login"
          class="col-sm text-secondary border rounded-lg m-0"
          :readonly="!isEdit"
          :value="currentPoint.readiness"
          v-model="currentPoint.readiness"
          aria-describedby="input-login-help input-login-feedback"
          trim
      />
    </div>

    <div class="row m-auto mt-xl-5">
      <div class="col-sm"/>
      <div class="col-5" v-if=" !isEdit">
        <button
            class=" w-100 btn btn-outline-warning rounded-lg"
            @click="editItem"
        >
          Edit
        </button>
      </div>
      <div class="col-5" v-if="isEdit">
        <button
            class="w-100 btn btn-outline-success rounded-lg"
            @click="saveItem"
        >
          Save
        </button>
      </div>
      <div class="col-sm"/>

    </div>

  </div>
</template>

<script>
import router from "@/router";
import {mapActions, mapGetters} from "vuex";


export default {
  name: "PlanPoint",
  data() {
    return {
      currentPoint: {},
      isEdit: false,
      isCreate: false,
    }
  },
  computed: {
    ...mapGetters([
      'PLAN_POINT',
      'PROJECTS',
      'ENGINEERS',
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
    getId() {
      const ppId = this.$route.params.id;
      return ppId
    },
  },
  methods: {
    ...mapActions([
      'GET_PLAN_POINT',
      'GET_ALL_ENGINEERS',
      'GET_ALL_PROJECTS',
      'SAVE_PLAN_POINT',

    ]),
    goBackToPlan() {
      router.go(-1);
    },
    editItem() {
      this.isEdit = true;
    },
    saveItem() {
      this.isEdit = false;
      this.currentPoint.id = this.getId;
      this.currentPoint.year = this.getYear;
      this.currentPoint.month = this.getMonth;
      this.currentPoint.departmentId = this.getDepartmentId;

      this.SAVE_PLAN_POINT(this.currentPoint).then(
          () => {
            this.goBackToPlan();
          }
      )
    },
  },
  mounted() {
    if (this.getId > 0) {
      this.GET_PLAN_POINT(this.getId)
          .then(() => {
            this.currentPoint = this.PLAN_POINT;
          });
      this.isEdit = false;
      this.isCreate = false;
    } else {
      this.currentPoint = {};
      this.isEdit = true;
      this.isCreate = true;
    }
    this.GET_ALL_ENGINEERS();
    this.GET_ALL_PROJECTS();
  }
}
</script>

<style scoped>

</style>