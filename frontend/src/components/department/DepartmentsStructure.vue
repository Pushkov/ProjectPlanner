<template>
  <div
      v-if="DEPARTMENTS !== undefined && DEPARTMENTS.length > 0"
      class="w-75 m-auto"
  >
    <table class="table borderless">
      <DepartmentStructureRow
          v-for="dep of DEPARTMENTS"
          :key="dep.id"
          :department="dep"
          :level="0"
      />
    </table>
  </div>
  <div v-else>
    {{ $t('message.department.no_list') }}
  </div>

</template>

<script>
import {mapActions, mapGetters} from 'vuex'
import DepartmentStructureRow from "@/components/department/DepartmentStructureRow";

export default {
  name: "DepartmentsStructure",
  components: {DepartmentStructureRow},
  computed: {
    ...mapGetters([
      'DEPARTMENTS'
    ]),
  },
  methods: {
    ...mapActions([
      'GET_STRUCTURE_DEPARTMENTS'
    ]),
  },
  mounted() {
    this.GET_STRUCTURE_DEPARTMENTS();
  }
}
</script>

<style scoped>

</style>