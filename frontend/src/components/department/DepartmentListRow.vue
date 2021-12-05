<template>
  <tbody>
  <tr @click="selectDepartment(department.id)">
    <td>
      {{ department.fullName }}
    </td>
  </tr>
  </tbody>
</template>

<script>
import router from "@/router";

export default {
  name: "DepartmentListRow",
  props: {
    department: {
      type: Object,
      default: () => {
        return {}
      },
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