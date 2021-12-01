<template>
  <div class="row p-3">
    <div class="col-sm">
      <div class="row">
        <h5 class="mx-3">Year:</h5>
      </div>
      <b-form-select
          v-model="currentYear"
          :options="years"
          class="row text-secondary border rounded-lg"
          value-field="value"
          text-field="value"
          disabled-field="notEnabled"
      ></b-form-select>
    </div>
    <div class="col-sm">
      <div class="row">
        <h5 class="mx-3">Month:</h5>
      </div>
      <b-form-select
          v-model="currentMonth"
          :options="months"
          class="row text-secondary border rounded-lg"
          value-field="value"
          text-field="value"
          disabled-field="notEnabled"
      ></b-form-select>
    </div>
    <div class="col-sm">
      <div class="row">
        <h5 class="mx-3">Department:</h5>
      </div>
      <b-form-select
          v-model="currentDepartment"
          :options="departments"
          class="row text-secondary border rounded-lg"
          value-field="value"
          text-field="value"
          disabled-field="notEnabled"
      ></b-form-select>
    </div>


  </div>
</template>

<script>
export default {
  name: "OverviewListSelectors",
  data() {
    return {
      currentYear: 2021,
      currentMonth: 11,
      currentDepartment: 'УПиР'
    }
  },
  props: {
    years: {
      type: Array,
      default:
          () => {
            return []
          }
    },
    months: {
      type: Array,
      default:
          () => {
            return []
          }
    },
    departments: {
      type: Array,
      default:
          () => {
            return []
          }
    },
  }
}

</script>

<style scoped>

</style>