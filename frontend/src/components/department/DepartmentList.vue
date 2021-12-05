<template>
  <div v-if="DEPARTMENTS !== undefined && DEPARTMENTS.length > 0"
       class="w-50 m-auto">
    <table class="table table-hover borderless">
      <DepartmentListRow
          v-for="dep of DEPARTMENTS"
          :key="dep.id"
          :department="dep"
      />
    </table>
  </div>
  <div v-else>
    {{ $t('message.department.no_list') }}
  </div>
</template>

<script>
import {mapActions, mapGetters} from "vuex";
import DepartmentListRow from "@/components/department/DepartmentListRow";

export default {
  name: "DepartmentList",
  components: {DepartmentListRow},
  computed: {
    ...mapGetters([
      'DEPARTMENTS'
    ]),
  },
  methods: {
    ...mapActions([
      'GET_ALL_DEPARTMENTS'
    ]),
  },
  mounted() {
    this.GET_ALL_DEPARTMENTS();
  }
}
</script>

<style scoped>

</style>