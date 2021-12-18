<template class=" mt-xl-5">
  <div class="w-75 m-auto">

    <div class="row m-auto">
      <b-button class="col-2 my-2" @click="createProject">
        {{ $t('button.create') }}
      </b-button>
      <div class="col-sm"/>
    </div>


    <template v-if="PROJECTS !== undefined && PROJECTS.length > 0">
      <ProjectsListRow

              v-for="prj of PROJECTS"
              :key="prj.id"
              :project="prj"
      />
    </template>
    <template v-else>
      ! PROJECT LIST IS EMPTY
    </template>
  </div>
</template>

<script>
  import {mapActions, mapGetters} from "vuex";
  import ProjectsListRow from "@/components/project/ProjectsListRow";
  import router from "../../router";

  export default {
    name: "ProjectsList",
    components: {ProjectsListRow},
    computed: {
      ...mapGetters([
        'PROJECTS'
      ]),
    },
    methods: {
      ...mapActions([
        'GET_ALL_PROJECTS'
      ]),
      createProject() {
        router.push('/planner/projects/0');
      }
    },
  mounted() {
    this.GET_ALL_PROJECTS();
  }
}
</script>

<style scoped>

</style>