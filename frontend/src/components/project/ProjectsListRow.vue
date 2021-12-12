<template class=" mt-xl-5">
  <div class="m-auto">


    <table class="table table-hover table-bordered table-striped">
      <thead class="thead-light">
      <tr>
        <th>Id</th>
        <th>{{ $t('project.designation') }}</th>
        <th>{{ $t('project.name') }}</th>
        <th>{{ $t('overview.department') }}</th>
        <th>{{ $t('list.workshop') }}</th>
        <th>{{ $t('overview.basics') }}</th>
        <th>{{ $t('project.status') }}</th>
      </tr>
      </thead>
      <tbody>
      <tr>
        <td>
          {{ project.id }}
        </td>
        <td>
          {{ project.designation }}
        </td>
        <td>
          {{ project.name }}
        </td>
        <td>
          {{ project.departmentName }}
        </td>
        <td>
          {{ project.workshopName }}
        </td>
        <td>
          {{ getBasics }}
        </td>
        <td>
          {{ project.status }}
        </td>
      </tr>

      </tbody>
    </table>


  </div>
</template>

<script>
export default {
  name: "ProjectsListRow",
  props: {
    project: {
      type: Object,
      default() {
        return {}
      }
    }
  },
  computed: {
    getBasics() {
      let basic = '';
      if (this.project.task !== null) {
        const t = this.project.task;
        basic = '№ ' + t.number + ' от ' + t.dateTime;
      } else if (this.project.memo !== null) {
        const mem = this.project.memo;
        basic = '№ ' + mem.number + ' от ' + mem.dateTime;
      }

      return basic;
    }

  },
}
</script>

<style scoped>

</style>