<template>
  <div>
    <NavigationBar/>
    <template v-if="this.$route.fullPath === '/planner/departments'">
      <template class="row my-2">
        <b-button
            class="col-3 mx-5 my-2"
            @click="hasStructure(false)"
        >
          {{ $t('message.department.list') }}
        </b-button>
        <b-button
            class="col-3 mx-5 my-2"
            @click="hasStructure(true)"
        >
          {{ $t('message.department.structure') }}
        </b-button>
      </template>
      <DepartmentsStructure v-if="IS_STRUCTURE"/>
      <DepartmentList v-else/>
    </template>
    <template v-else>
      <Department/>
    </template>
  </div>
</template>

<script>
import NavigationBar from "@/components/nav/NavigationBar";
import DepartmentList from "@/components/department/DepartmentList";
import DepartmentsStructure from "@/components/department/DepartmentsStructure";
import Department from "@/components/department/Department";
import {mapActions, mapGetters} from "vuex";

export default {
  name: "DepartmentPage",
  data() {
    return {}
  },
  components: {
    Department,
    DepartmentsStructure,
    DepartmentList,
    NavigationBar
  },
  computed: {
    ...mapGetters([
      'IS_STRUCTURE'
    ]),
  },
  methods: {
    ...mapActions([
      'SET_IS_STRUCTURE'
    ]),
    hasStructure(value) {
      this.SET_IS_STRUCTURE(value);
    },
  },
}
</script>

<style scoped>

</style>