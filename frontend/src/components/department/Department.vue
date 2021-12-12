<template>
  <div>
    <div v-if="DEPARTMENT !== undefined && DEPARTMENT !== {}"
         class="w-50 m-auto">
      <div class="my-2"><h4>{{ DEPARTMENT.fullName }}</h4></div>
      <table class="table ">
        <tbody>
        <tr>
          <td>
            {{ $t('message.department.name') }}
          </td>
          <td>
            {{ DEPARTMENT.name }}
          </td>
        </tr>
        <tr v-if="DEPARTMENT.engineers !== undefined && DEPARTMENT.engineers.length > 0">
          <td>
            {{ $t('message.department.personal') }}
          </td>
          <td>
            <div
                v-for="eng of DEPARTMENT.engineers "
                :key="eng.id"
            >
              {{ eng.lastName }} {{ eng.firstName }} {{ eng.patronymicName }}
            </div>
          </td>
        </tr>
        <tr>
          <td>
            {{ $t('message.department.workshops') }}
          </td>
          <td v-if="DEPARTMENT.workshops !== undefined && DEPARTMENT.workshops.length > 0">
            <div
                v-for="ws of DEPARTMENT.workshops "
                :key="ws.id"
            >
              {{ ws.name }}
            </div>
          </td>
          <td v-else>
            {{ $t('message.department.all_plant') }}
          </td>
        </tr>
        </tbody>
      </table>
      <div class="row">
        <b-button class="col-3 m-auto mt-xl-5" @click="goBack">
          <h5 class="m-auto">
            {{ $t('message.button.back') }}
          </h5>
        </b-button>
        <b-button class="col-3 m-auto mt-xl-5 bg-light btn btn-outline-warning text-warning " @click="editDepartment">
          <h5 class="m-auto">
            {{ $t('message.button.edit') }}
          </h5>
        </b-button>
        <b-button class="col-3 m-auto mt-xl-5 bg-light btn btn-outline-danger text-danger " @click="deleteDepartment">
          <h5 class="m-auto">
            {{ $t('message.button.delete') }}
          </h5>
        </b-button>
      </div>
    </div>
    <div v-else>
      {{ $t('message.department.no_list') }}
    </div>

  </div>
</template>

<script>
import router from "@/router";
import {mapActions, mapGetters} from "vuex";

export default {
  name: "Department",
  data() {
    return {
      isEdit: false,
      id: 0,
      currentDepartment: {...this.DEPARTMENT}
    }
  },
  props: {
    department: {
      type: Object,
      default: () => {
        return {}
      }
    }
  },
  computed: {
    ...mapGetters([
      'DEPARTMENT'
    ]),
    getDepartmentId() {
      return this.$route.params.id;
    },

  },
  methods: {
    ...mapActions([
      'GET_DEPARTMENT',
      'DELETE_DEPARTMENT'
    ]),
    goBack() {
      router.go(-1)
    },
    editDepartment() {
      this.isEdit = true;
    },
    deleteDepartment() {
      this.DELETE_DEPARTMENT(this.id).then(
          this.goBack()
      );
    }
  },
  mounted() {
    this.id = this.$route.params.id;
    this.GET_DEPARTMENT(this.id);
  }
}
</script>

<style scoped>

</style>