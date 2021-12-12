<template>
  <div>
    <template v-if="PROJECTS !== undefined && PROJECTS.length > 0">
      <ProjectsListRow
          v-for="prj of PROJECTS"
          :key="prj.id"
          :project="prj"
      />
    </template>
    <templa v-else>
      ! PROJECT LIST IS EMPTY
    </templa>
  </div>
</template>

<script>
import {mapActions, mapGetters} from "vuex";
import ProjectsListRow from "@/components/project/ProjectsListRow";

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
  },
  mounted() {
    this.GET_ALL_PROJECTS();
  }
}
</script>

<style scoped>

</style>