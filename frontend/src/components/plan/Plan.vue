<template>
  <div>
    PLAN PAGE
    <br/>
    <div v-if="getYear !== undefined">
      {{ getYear }}
    </div>
    <div v-if="getMonth !== undefined">
      {{ getMonth }}
    </div>
    <div v-if="getDepartmentId !== undefined">
      {{ getDepartmentId }}
    </div>
    <div v-if="PLAN !== undefined">
      {{ PLAN }}
    </div>
  </div>
</template>

<script>
import {mapActions, mapGetters} from "vuex";

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
    }
  },
  methods: {
    ...mapActions([
      'GET_PLAN'
    ]),
  },
  mounted() {
    const plan = {
      'year': this.year,
      'month': this.month,
      'department_id': this.department_id,
    }
    this.GET_PLAN(plan);
  }
}
</script>

<style scoped>

</style>