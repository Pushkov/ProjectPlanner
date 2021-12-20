<template class=" mt-xl-5">
  <div class="w-75 m-auto">
    <div class="row m-auto">
      <b-button class="col-2 my-2" @click="toProjects">
        {{ $t('button.back') }}
      </b-button>
      <div class="col-sm">

      </div>
    </div> <!--HEADER BUTTON BACK-->


    <div class="row my-2">
      <div class="col-4 m-auto mr-2 ">
        {{ $t('project.status') }}
      </div>
      <div class="col-1"/>
      <b-form-input
          v-if="!isEdit"
          id="input-login"
          class="col-sm text-secondary border rounded-lg m-0"
          readonly
          :value="currentProject.status"
          aria-describedby="input-login-help input-login-feedback"
          trim
      />
      <b-form-select
          v-else-if="isEdit"
          v-model="currentProject.status"
          :options="getStatuses"
          class="col-sm text-secondary border rounded-lg m-0"
          value-field="name"
          text-field="name"
          disabled-field="notEnabled"
      />
    </div>

    <div class="row my-2">
      <div class="col-4 m-auto mr-2 ">
        {{ $t('project.designation') }}
      </div>
      <div class="col-1"/>
      <b-form-input
          id="input-login"
          class="col-sm text-secondary border rounded-lg m-0"
          :readonly="!isEdit"
          :value="currentProject.designation"
          v-model="currentProject.designation"
          aria-describedby="input-login-help input-login-feedback"
          trim
      />
    </div>

    <div class="row my-2">
      <div class="col-4 m-auto mr-2 ">
        {{ $t('project.name') }}
      </div>
      <div class="col-1"/>
      <b-form-input
          id="input-login"
          class="col-sm text-secondary border rounded-lg m-0"
          :readonly="!isEdit"
          :value="currentProject.name"
          v-model="currentProject.name"
          aria-describedby="input-login-help input-login-feedback"
          trim
      />
    </div>

    <div class="row my-2">
      <div class="col-4 m-auto mr-2 ">
        {{ $t('list.workshop') }}
      </div>
      <div class="col-1"/>
      <b-form-input
          v-if="!isEdit"
          id="input-login"
          class="col-sm text-secondary border rounded-lg m-0"
          readonly
          :value="currentProject.workshopName"
          aria-describedby="input-login-help input-login-feedback"
          trim
      />
      <b-form-select
          v-else-if="isEdit"
          v-model="currentProject.workshopId"
          :options="WORKSHOPS"
          class="col-sm text-secondary border rounded-lg m-0"
          value-field="id"
          text-field="name"
          disabled-field="notEnabled"
      />
    </div>


    <div class="row my-2">
      <div class="col-4 m-auto mr-2 ">
        {{ $t('project.department') }}
      </div>
      <div class="col-1"/>
      <b-form-input
          v-if="!isEdit"
          id="input-login"
          class="col-sm text-secondary border rounded-lg m-0"
          readonly
          :value="currentProject.departmentName"
          aria-describedby="input-login-help input-login-feedback"
          trim
      />
      <b-form-select
          v-else-if="isEdit"
          v-model="currentProject.departmentId"
          :options="DEPARTMENTS"
          class="col-sm text-secondary border rounded-lg m-0"
          value-field="id"
          text-field="name"
          disabled-field="notEnabled"
      />
    </div>
    <!--TASK-->
    <div class="row my-2">
      <div class="col-4 m-auto mr-2 ">
        {{ $t('list.tasks_list') }}
      </div>
      <div class="col-1"/>

      <b-form-input
          v-if="!isEdit"
          id="input-login"
          class="col-sm text-secondary border rounded-lg m-0"
          readonly
          :value="getTask"
          aria-describedby="input-login-help input-login-feedback"
          trim
      />
      <b-form-select
          v-else-if="isEdit"
          v-model="currentProject.task.id"
          :options="getAllTasks"
          class="col-sm text-secondary border rounded-lg m-0"
          value-field="id"
          text-field="name"
          disabled-field="notEnabled"
          @change="selectTaskToProject"
      />

    </div>
    <div class="row my-2">
      <div class="col-4 m-auto mr-2 ">
        {{ $t('list.memos_list') }}
      </div>
      <div class="col-1"/>
      <b-form-input
          v-if="!isEdit"
          id="input-login"
          class="col-sm text-secondary border rounded-lg m-0"
          readonly
          :value="getMemo"
          aria-describedby="input-login-help input-login-feedback"
          trim
      />
      <b-form-select
          v-else-if="isEdit"
          v-model="currentProject.memo.id"
          :options="getAllMemos"
          class="col-sm text-secondary border rounded-lg m-0"
          value-field="id"
          text-field="number"
          disabled-field="notEnabled"
          @change="selectMemoToProject"
      />
    </div>


    <div class="row my-2">
      <div class="col-4 m-auto mr-2 ">
        {{ $t('document.included_in') }}
      </div>
      <plus-icon
          v-if="isEdit"
          class="col-1"
          fill-color="green"
          @click="addBasicProject"
      />
      <div v-if="!isEdit" class="col-1"/>
      <div class="col-sm text-left text-secondary border rounded-lg m-0" :style="getReadOnlyStyle">
        <template v-if="currentProject.basicProject !== undefined && currentProject.basicProject.length > 0">
          <div
              v-for="bp of currentProject.basicProject"
              :key="bp.id"
          >{{ bp.designation }}
          </div>
        </template>
      </div>
    </div>

    <div class="row my-2">
      <div class="col-4 m-auto mr-2 ">
        {{ $t('document.refers_to') }}
      </div>
      <div class="col-1"/>
      <div class="col-sm text-left text-secondary border rounded-lg m-0" style="background: #e9ecef">
        <template v-if="currentProject.projects !== undefined && currentProject.projects.length > 0">
          <div v-for="p of currentProject.projects"
               :key="p.id"
          >{{ p.designation }}
          </div>
        </template>
      </div>
    </div>

    <div class="row my-2">
      <div class="col-4 m-auto mr-2 ">
        {{ $t('project.included_in_plan') }}
      </div>
      <div class="col-1"/>
      <div class="col-sm text-left text-secondary border rounded-lg m-0" style="background: #e9ecef">
        <template v-if="currentProject.planPoints !== undefined && currentProject.planPoints.length > 0">
          <div v-for="pp of currentProject.planPoints"
               :key="pp.id"
          >{{ pp.departmentName }} - {{ getMonth(pp.month) }} - {{ pp.year }}
          </div>
        </template>
      </div>
    </div>

    <div class="row my-2">
      <div class="col-4 m-auto mr-2 ">
        {{ $t('project.refers_to_docs') }}
      </div>
      <div class="col-1"/>
      <div class="col-sm text-left text-secondary border rounded-lg m-0" style="background: #e9ecef">
        <template v-if="currentProject.documentsDto !== undefined && currentProject.documentsDto.length > 0">
          <div v-for="d of currentProject.documentsDto"
               :key="d.id"
          >{{ d.designation }}
          </div>
        </template>
      </div>
    </div>

    <!--APPROVALS START-->

    <div class="row my-2">
      <div class="col-4 m-auto mr-2 ">
        {{ $t('project.designer') }}
      </div>
      <div class="col-1"/>
      <b-form-input
          v-if="!isEdit"
          readonly
          id="input-login"
          class="col-sm text-secondary border rounded-lg m-0"
          :value="currentProject.projectApprovalsDto.designerName"
          aria-describedby="input-login-help input-login-feedback"
          trim
      />
      <b-form-select
          v-else-if="isEdit"
          v-model="currentProject.projectApprovalsDto.designerId"
          :options="ENGINEERS"
          class="col-sm text-secondary border rounded-lg m-0"
          value-field="id"
          text-field="lastName"
          disabled-field="notEnabled"
      />
    </div>
    <div class="row my-2">
      <div class="col-4 m-auto mr-2 ">
        {{ $t('document.date') }}
      </div>
      <div class="col-1"/>
      <div class="col-sm row">
        <b-form-input
            class="col-sm"
            readonly
            :value="currentProject.projectApprovalsDto.designerSign"
            :class="getErrorDate"
            trim
        />
        <b-form-datepicker
            class="col-auto"
            right
            button-only
            :disabled="!isEdit"
            :max="getNowDate"
            :date-format-options="{ year: 'numeric', month: '2-digit', day: '2-digit' }"
            locale="RU"
            @context="setDesignerDate"
        ></b-form-datepicker>
      </div>

    </div>

    <div class="row my-2">
      <div class="col-4 m-auto mr-2 ">
        {{ $t('project.verifier') }}
      </div>
      <div class="col-1"/>
      <b-form-input
          v-if="!isEdit"
          readonly
          id="input-login"
          class="col-sm text-secondary border rounded-lg m-0"
          :value="currentProject.projectApprovalsDto.verifierName"
          aria-describedby="input-login-help input-login-feedback"
          trim
      />
      <b-form-select
          v-else-if="isEdit"
          v-model="currentProject.projectApprovalsDto.verifierId"
          :options="ENGINEERS"
          class="col-sm text-secondary border rounded-lg m-0"
          value-field="id"
          text-field="lastName"
          disabled-field="notEnabled"
      />
    </div>
    <div class="row my-2">
      <div class="col-4 m-auto mr-2 ">
        {{ $t('document.date') }}
      </div>
      <div class="col-1"/>
      <div class="col-sm row">
        <b-form-input
            class="col-sm"
            readonly
            :value="currentProject.projectApprovalsDto.verifierSign"
            :class="getErrorDate"
            trim
        />
        <b-form-datepicker
            class="col-auto"
            right
            button-only
            :disabled="!isEdit"
            :max="getNowDate"
            :date-format-options="{ year: 'numeric', month: '2-digit', day: '2-digit' }"
            locale="RU"
            @context="setVerifierDate"
        ></b-form-datepicker>
      </div>

    </div>

    <div class="row my-2">
      <div class="col-4 m-auto mr-2 ">
        {{ $t('project.norm_contr') }}
      </div>
      <div class="col-1"/>
      <b-form-input
          v-if="!isEdit"
          readonly
          id="input-login"
          class="col-sm text-secondary border rounded-lg m-0"
          :value="currentProject.projectApprovalsDto.normControlName"
          aria-describedby="input-login-help input-login-feedback"
          trim
      />
      <b-form-select
          v-else-if="isEdit"
          v-model="currentProject.projectApprovalsDto.normControlId"
          :options="ENGINEERS"
          class="col-sm text-secondary border rounded-lg m-0"
          value-field="id"
          text-field="lastName"
          disabled-field="notEnabled"
      />
    </div>
    <div class="row my-2">
      <div class="col-4 m-auto mr-2 ">
        {{ $t('document.date') }}
      </div>
      <div class="col-1"/>
      <div class="col-sm row">
        <b-form-input
            class="col-sm"
            readonly
            :value="currentProject.projectApprovalsDto.normControlSign"
            :class="getErrorDate"
            trim
        />
        <b-form-datepicker
            class="col-auto"
            right
            button-only
            :disabled="!isEdit"
            :max="getNowDate"
            :date-format-options="{ year: 'numeric', month: '2-digit', day: '2-digit' }"
            locale="RU"
            @context="setNormControlDate"
        ></b-form-datepicker>
      </div>
    </div>

    <div class="row my-2">
      <div class="col-4 m-auto mr-2 ">
        {{ $t('project.agree') }}
      </div>
      <div class="col-1"/>
      <b-form-input
          v-if="!isEdit"
          readonly
          id="input-login"
          class="col-sm text-secondary border rounded-lg m-0"
          :value="currentProject.projectApprovalsDto.agreeName"
          aria-describedby="input-login-help input-login-feedback"
      />
      <b-form-select
          v-else-if="isEdit"
          v-model="currentProject.projectApprovalsDto.agreeId"
          :options="ENGINEERS"
          class="col-sm text-secondary border rounded-lg m-0"
          value-field="id"
          text-field="lastName"
          disabled-field="notEnabled"
      />
    </div>
    <div class="row my-2">
      <div class="col-4 m-auto mr-2 ">
        {{ $t('document.date') }}
      </div>
      <div class="col-1"/>
      <div class="col-sm row">
        <b-form-input
            class="col-sm"
            readonly
            :value="currentProject.projectApprovalsDto.agreeSign"
            :class="getErrorDate"
            trim
        />
        <b-form-datepicker
            class="col-auto"
            right
            button-only
            :disabled="!isEdit"
            :max="getNowDate"
            :date-format-options="{ year: 'numeric', month: '2-digit', day: '2-digit' }"
            locale="RU"
            @context="setAgreeDate"
        ></b-form-datepicker>
      </div>
    </div>

    <div class="row my-2">
      <div class="col-4 m-auto mr-2 ">
        {{ $t('project.approve') }}
      </div>
      <div class="col-1"/>
      <b-form-input
          v-if="!isEdit"
          readonly
          id="input-login"
          class="col-sm text-secondary border rounded-lg m-0"
          :value="currentProject.projectApprovalsDto.approveName"
          aria-describedby="input-login-help input-login-feedback"
          trim
      />
      <b-form-select
          v-else-if="isEdit"
          v-model="currentProject.projectApprovalsDto.approveId"
          :options="ENGINEERS"
          class="col-sm text-secondary border rounded-lg m-0"
          value-field="id"
          text-field="lastName"
          disabled-field="notEnabled"
      />
    </div>
    <div class="row my-2">
      <div class="col-4 m-auto mr-2 ">
        {{ $t('document.date') }}
      </div>
      <div class="col-1"/>
      <div class="col-sm row">
        <b-form-input
            class="col-sm"
            readonly
            :value="currentProject.projectApprovalsDto.approveSign"
            :class="getErrorDate"
            trim
        />
        <b-form-datepicker
            class="col-auto"
            right
            button-only
            :disabled="!isEdit"
            :max="getNowDate"
            :date-format-options="{ year: 'numeric', month: '2-digit', day: '2-digit' }"
            locale="RU"
            @context="setApproveDate"
        ></b-form-datepicker>
      </div>

    </div>

    <!--APPROVALS END-->

    <div class="row m-auto mt-xl-5">
      <div class="col-sm"/>
      <div class="col-5" v-if=" !isEdit">
        <button
            class=" w-100 btn btn-outline-warning rounded-lg"
            @click="editItem"
        >
          Edit
        </button>
      </div>
      <div class="col-5" v-if="isEdit">
        <button
            class="w-100 btn btn-outline-success rounded-lg"
            @click="saveItem"
        >
          Save
        </button>
      </div>
      <div class="col-sm"/>
      <div class="col-5" v-if="isEdit">
        <button
            class="w-100 btn btn-outline-danger rounded-lg"
            @click="deleteProject"
        >
          Delete
        </button>
      </div>
      <div class="col-sm" v-if="isEdit"/>
    </div> <!-- FOOTER BUTTONS EDIT-SAVE-->
  </div>
</template>


<script>
  import {mapActions, mapGetters} from "vuex";
  import router from "../../router";
  import moment from "moment";

  export default {
    name: "Project",
    data() {
      return {
        currentProject: {
          status: 'Не приступали',
          projectApprovalsDto: {},
          task: {
            id: 0,
          },
          memo: {
          id: 0,
        }
      },
      isEdit: false,
      isCreate: false,
    }
  },
  computed: {
    ...mapGetters([
      'PROJECT',
      'WORKSHOPS',
      'DEPARTMENTS',
      'ALL_LOCALE_MONTHS',
      'ENGINEERS',
      'TITLE_LIST',
      'TITLE_LISTS',
    ]),
    getNowDate() {
      const now = new Date()
      const today = new Date(now.getFullYear(), now.getMonth(), now.getDate())
      const minDate = new Date(today)
      return minDate;
    },
    getTask() {
      if (this.currentProject.task !== undefined && this.currentProject.task !== null && this.currentProject.task.id > 0) {
        return this.currentProject.task.number + ' от ' + this.currentProject.task.date;
      }
      return ''
    },
    getMemo() {
      if (this.currentProject.memo !== undefined && this.currentProject.memo !== null && this.currentProject.memo.id > 0) {
        return this.currentProject.memo.number + ' от ' + this.currentProject.memo.date;
      }
      return ''

    },
    getId() {
      return this.$route.params.id;
    },
    getStatuses() {
      return ['В работе', 'Не приступали', 'Выполнен']

    },
    getReadOnlyStyle() {
      return this.isEdit ? '' : 'background: #e9ecef'
    },
    getErrorDate() {
      return false
    },
    getAllTasks() {
      let tasks = [];
      const titles = this.TITLE_LISTS;
      if (titles !== undefined && titles.length > 0) {
        for (const title of titles) {
          if (title.technicalTaskDto !== undefined && title.technicalTaskDto.length > 0) {
            for (const tt of title.technicalTaskDto) {
              tasks.push(tt);
            }
          }
        }
        return tasks;
      } else if (this.TITLE_LIST.technicalTaskDto !== undefined && this.TITLE_LIST.technicalTaskDto.length > 0) {
        return this.TITLE_LIST.technicalTaskDto
      } else {
        return tasks;
      }
    }
    ,
    getAllMemos() {
      let memos = [];
      const titles = this.TITLE_LISTS;
      if (titles !== undefined && titles.length > 0) {
        for (const title of titles) {
          if (title.memoDto !== undefined && title.memoDto.length > 0) {
            for (const mem of title.memoDto) {
              memos.push(mem);
            }
          }
        }
        return memos;
      } else if (this.TITLE_LIST.memoDto !== undefined && this.TITLE_LIST.memoDto.length > 0) {
        return this.TITLE_LIST.memoDto
      } else {
        return memos;
      }
    },


  },
  methods: {
    ...mapActions([
      'GET_ALL_PROJECTS',
      'GET_PROJECT',
      'GET_ALL_WORKSHOPS',
      'GET_ALL_DEPARTMENTS',
      'GET_ALL_ENGINEERS',
      'CREATE_PROJECT',
      'UPDATE_PROJECT',
      'DELETE_PROJECT',
      'GET_TITLE_LIST',
    ]),
    toProjects() {
      router.push('/planner/projects');
    },

    editItem() {
      this.isEdit = true;
    },
    saveItem() {
      this.isEdit = false;
      if (this.getId == 0) {
        this.CREATE_PROJECT(this.currentProject)
      } else {
        this.UPDATE_PROJECT(this.currentProject)
      }
      router.push('/planner/projects')
    },
    getMonth(value) {
      return this.ALL_LOCALE_MONTHS.filter(x => x.value === value)[0].name;
    },
    addBasicProject() {
    },
    deleteProject() {
      this.DELETE_PROJECT(this.currentProject)
      router.push('/planner/projects')
    },
    getBasics() {
      let res = [];
      res.push(this.getAllTasks());
      res.push(this.getAllMemos());

      return res;
    },

    setDesignerDate(value) {
      const date = moment(value.selectedYMD).format('DD-MM-YYYY');
      if (date !== 'Invalid date') {
        this.currentProject.projectApprovalsDto.designerSign = date;
      }
    },
    setVerifierDate(value) {
      const date = moment(value.selectedYMD).format('DD-MM-YYYY');
      if (date !== 'Invalid date') {
        this.currentProject.projectApprovalsDto.verifierSign = date;
      }
    },
    setNormControlDate(value) {
      const date = moment(value.selectedYMD).format('DD-MM-YYYY');
      if (date !== 'Invalid date') {
        this.currentProject.projectApprovalsDto.normControlSign = date;
      }
    },
    setAgreeDate(value) {
      const date = moment(value.selectedYMD).format('DD-MM-YYYY');
      if (date !== 'Invalid date') {
        this.currentProject.projectApprovalsDto.approveSign = date;
      }
    },
    setApproveDate(value) {
      const date = moment(value.selectedYMD).format('DD-MM-YYYY');
      if (date !== 'Invalid date') {
        this.currentProject.projectApprovalsDto.approveSign = date;
      }
    },
    selectTaskToProject() {
      this.currentProject.memo.id = 0
    },
    selectMemoToProject() {
      this.currentProject.task.id = 0
    },


  },
  mounted() {
    this.GET_ALL_WORKSHOPS();
    this.GET_ALL_DEPARTMENTS();
    this.GET_ALL_ENGINEERS();
    this.GET_TITLE_LIST(-1);

    if (this.getId == 0) {
      this.isEdit = true;
      this.isCreate = true;
      this.currentProject.projectApprovalsDto = {
        designerSign: '',
        verifierSign: '',
        normControlSign: '',
        agreeSign: '',
        approveSign: ''
      };
    } else {
      this.isCreate = false;
      this.GET_PROJECT(this.getId).then(() => {
        this.currentProject = this.PROJECT;
        if (this.currentProject.projectApprovalsDto === undefined || this.currentProject.projectApprovalsDto === null) {
          this.currentProject.projectApprovalsDto = {
            designerSign: '',
            verifierSign: '',
            normControlSign: '',
            agreeSign: '',
            approveSign: ''
          };

        }
        if (this.currentProject.task === undefined || this.currentProject.task === null) {
          this.currentProject.task = {};
          this.currentProject.task.id = 0;
        }
        if (this.currentProject.memo === undefined || this.currentProject.memo === null) {
          this.currentProject.memo = {};
          this.currentProject.memo.id = 0;
        }
      })

    }

  }
}
</script>

<style scoped>

</style>