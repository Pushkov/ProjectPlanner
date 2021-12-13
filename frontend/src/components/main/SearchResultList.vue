<template class=" mt-xl-5">
  <div class="w-75 m-auto">


      <div v-if="SEARCH_RESULT.documentSimpleDtoList !== undefined && SEARCH_RESULT.documentSimpleDtoList.length > 0">
          <h4> Найдены документы:</h4>
          <div class=" row text-left my-3"
               v-for="doc of SEARCH_RESULT.documentSimpleDtoList"
               :key="doc.id"
               @click="selectDocument(doc.id)"
          >
              {{$t('document.name')}}:: {{ doc.name }}
          </div>
      </div>
      <div v-else-if="term.trim() !== ''">
          <!--      documents {{ ERROR_SEARCH_LOADING_MESSAGE }}-->
      </div>
      <div v-else>
      </div>
      <div v-if="SEARCH_RESULT.projectDtoList !== undefined && SEARCH_RESULT.projectDtoList.length > 0">
          <h4> Найдены проекты:</h4>
          <div class=" row text-left my-3"
               v-for="prj of SEARCH_RESULT.projectDtoList"
               :key="prj.id"
          >
              {{$t('project.designation')}}: {{ prj.designation }}; {{$t('project.name')}}: {{ prj.name }}
          </div>

      </div>
      <div v-else-if="term.trim() !== ''">
          <!--      projects {{ ERROR_SEARCH_LOADING_MESSAGE }}-->
      </div>
      <div v-else>
      </div>

      <div v-if="SEARCH_RESULT.engineerDtoList !== undefined && SEARCH_RESULT.engineerDtoList.length > 0">
          <h4> Найдены инженеры:</h4>
      <div class=" row text-left my-3"
           v-for="eng of SEARCH_RESULT.engineerDtoList"
           :key="eng.id"
      >
          {{$t('message.engineer.last_name')}}: {{ eng.lastName }} {{$t('message.engineer.first_name')}}: {{
          eng.firstName }}
      </div>

    </div>
    <div v-else-if="term.trim() !== ''">
        <!--      engineers {{ ERROR_SEARCH_LOADING_MESSAGE }}-->
    </div>
    <div v-else>
    </div>
    <div class=" row text-left my-3">
      <h5 v-if="EXECUTION_TIME >= 0">Время выполнения запроса: {{ EXECUTION_TIME }} мс</h5>
    </div>
  </div>
</template>

<script>
    import {mapActions, mapGetters} from 'vuex'
    import router from "../../router";

    export default {
        name: "SearchResultList",
        data() {
            return {
                resultList: {}
            }
        },
        props: {
            term: {
                type: String,
                default: ''
            },
            isDocument: {
                type: Boolean,
                default: false
            },
            isProject: {
                type: Boolean,
                default: false
            },
            isEngineer: {
                type: Boolean,
                default: false
            }
        },
  components: {},
  computed: {
    ...mapGetters([
      'SEARCH_RESULT',
      'EXECUTION_TIME',
      'ERROR_SEARCH_LOADING_MESSAGE'
    ]),
  },
  methods: {
    ...mapActions([
        'GET_SEARCH_RESULT'
    ]),
      refresh() {
          let par = {
              "term": this.term,
              "document": this.isDocument,
              "project": this.isProject,
              "engineer": this.isEngineer
          }
          this.GET_SEARCH_RESULT(par);
      },
      selectDocument(id) {
          router.push('/planner/documents/' + id)
      }
  },
  watch: {
      term: function (val) {
          this.term = val;
          this.refresh();
      },
      isDocument: function (val) {
          this.isDocument = val;
          this.refresh();
      },
      isProject: function (val) {
          this.isProject = val;
          this.refresh();
      },
      isEngineer: function (val) {
          this.isEngineer = val;
          this.refresh();
      }
  },
  mounted() {
  }
}
</script>

<style scoped>

</style>