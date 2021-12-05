<template>
  <tbody>
  <tr class="row" @click="selectDepartment(department.id)">
    <td class="col-sm">
      {{ department.fullName }}
    </td>
  </tr>
  <tr>
    <td
        v-if="department.innerDepartments !== undefined && department.innerDepartments.length > 0">
      <!--      {{ department.name }}-->
    </td>
    <template v-if="department.innerDepartments !== undefined && department.innerDepartments.length > 0">
      <DepartmentStructureRow
          v-for="inn of department.innerDepartments"
          :key="inn.id"
          :department="inn"
          :level="getLevel"
      />
    </template>
  </tr>
  </tbody>
</template>

<script>
import router from "@/router";

export default {
  name: "DepartmentStructureRow",
  props: {
    department: {
      type: Object,
      default: () => {
        return {}
      },
    },
    level: {
      type: Number,
      default: 1
    },
  },
  computed: {
    getLevel() {
      return this.level + 1;
    },

  },
  methods: {
    selectDepartment(id) {
      router.push('/planner/departments/' + id);
    }
  }
}
</script>

<style scoped>

</style>