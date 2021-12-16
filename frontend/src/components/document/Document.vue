<template>
  <div v-if="currentDocument !== undefined " class="w-75 m-auto">

    <BasicModal
        v-if="isAddProjectShow"
        :popup-title="getDocumentModalTitle"
        :is-edit="isAddProjectEdit"
        @closeModal="closeModal"
    >
      <DocumentProjectModal
          ref="documentAddProject"
          :engineers="PROJECTS"
          @returnItem="returnProject"
      />
      <DocumentProjectModalFooter
          slot="footer"
          @modalClose="closeModal"
          @saveProject='saveProject'
          @deleteItem="removeProject"
          @editItem="closeModal"
          :is-edit="isAddProjectEdit"
          :is-create="isAddProjectCreate"
      />
    </BasicModal>


    <BasicModal
        v-if="MODAL_SHOW"
        :popup-title="getDocumentModalTitle"
        :is-edit="MODAL_EDIT"
        @closeModal="closeModal"
    >
      <DocumentFormatModal
          ref="documentAddFormat"
          :engineers="getDocFormats"
          @returnItem="returnItem"
      />
      <RolePermissionsModalFooter
          slot="footer"
          @modalClose="closeModal"
          @addItem='saveAddFormat'
          @deleteItem="closeModal"
          @editItem="closeModal"
          :is-edit="MODAL_EDIT"
          :is-create="MODAL_CREATE"
      />
    </BasicModal>


    <div class="my-2"><h4>{{ currentDocument.designation }}</h4></div>
    <table class="table ">
      <tbody>
      <tr>
        <td class="col-5">
          {{ $t('document.designation') }}
        </td>
        <td colspan="2">
          <b-input
              class="col-sm text-secondary  border rounded-lg"
              :readonly="!isEdit || MODAL_SHOW"
              :value="currentDocument.designation "
              v-model="currentDocument.designation "/>

        </td>
      </tr>
      <tr>
        <td>
          {{ $t('document.name') }}
        </td>
        <td colspan="2">
          <b-input
              class="col-sm text-secondary  border rounded-lg"
              :readonly="!isEdit"
              :value="currentDocument.name "
              v-model="currentDocument.name "/>
        </td>
      </tr>

      <tr>
        <td>
          <div class="col-sm"/>
          {{ $t('navigation.projects') }}
          <plus-icon
              v-if="isEdit"
              class="col-1"
              fill-color="green"
              @click="addProject"
          />
        </td>
        <td>
          <div class="col-sm text-secondary ">
            <template v-if="currentDocument.projects !== undefined && currentDocument.projects.length > 0">
              <div class="row"
                   v-for="p of currentDocument.projects"
                   :key="p.id"
              >
                <div class="col-sm">{{ p.designation }}</div>

                <minus-icon
                    class="col-1  my-2"
                    fill-color="red"
                    v-if="isEdit"
                    @click="removeProject(p)"
                />

              </div>
            </template>
            <template v-else>
              <div>&nbsp;</div>
            </template>
          </div>
        </td>
      </tr>

      <tr v-if="currentDocument.documentApprovalsDto !== undefined &&  (currentDocument.documentApprovalsDto.designerName !== undefined  || isCreate || isSigned)">
        <td>
          {{ $t('document.designer') }}
        </td>
        <td>
          <b-input
              v-if="!isEdit"
              class="col-sm text-secondary border rounded-lg"
              readonly
              :value="currentDocument.documentApprovalsDto.designerName"
          />
          <b-form-select
              v-else-if="isEdit"
              v-model="currentDocument.documentApprovalsDto.designerId"
              :options="ENGINEERS"
              class="col-sm text-secondary border rounded-lg"
              value-field="id"
              text-field="lastName"
              disabled-field="notEnabled"
          />
        </td>
        <td>
          <b-input
              class="col-sm text-secondary  border rounded-lg"
              :readonly="!isEdit"
              :value="currentDocument.documentApprovalsDto.designerSign"
              v-model="currentDocument.documentApprovalsDto.designerSign"/>
        </td>
      </tr>
      <tr v-if="currentDocument.documentApprovalsDto !== undefined &&  (currentDocument.documentApprovalsDto.verifierName !== undefined   || isCreate || isSigned)">
        <td>
          {{ $t('document.verifier') }}
        </td>
        <td>
          <b-input
              v-if="!isEdit"
              class="col-sm text-secondary border rounded-lg"
              readonly
              :value="currentDocument.documentApprovalsDto.verifierName"
          />
          <b-form-select
              v-else-if="isEdit"
              v-model="currentDocument.documentApprovalsDto.verifierId"
              :options="ENGINEERS"
              class="col-sm text-secondary border rounded-lg"
              value-field="id"
              text-field="lastName"
              disabled-field="notEnabled"
          />

        </td>
        <td>
          <b-input
              class="col-sm text-secondary  border rounded-lg"
              :readonly="!isEdit"
              :value="currentDocument.documentApprovalsDto.verifierSign "
              v-model="currentDocument.documentApprovalsDto.verifierSign"/>
        </td>
      </tr>
      <tr v-if="currentDocument.documentApprovalsDto !== undefined &&  (currentDocument.documentApprovalsDto.normControlName !== undefined   || isCreate || isSigned)">
        <td>
          {{ $t('document.norm_contr') }}
        </td>
        <td>
          <b-input
              v-if="!isEdit"
              class="col-sm text-secondary border rounded-lg"
              readonly
              :value="currentDocument.documentApprovalsDto.normControlName"
          />
          <b-form-select
              v-else-if="isEdit"
              v-model="currentDocument.documentApprovalsDto.normControlId"
              :options="ENGINEERS"
              class="col-sm text-secondary border rounded-lg"
              value-field="id"
              text-field="lastName"
              disabled-field="notEnabled"
          />

        </td>
        <td>
          <b-input
              class="col-sm text-secondary  border rounded-lg"
              :readonly="!isEdit"
              :value="currentDocument.documentApprovalsDto.normControlSign "
              v-model="currentDocument.documentApprovalsDto.normControlSign"/>
        </td>
      </tr>
      <tr v-if="currentDocument.documentFormatDto !== undefined">
        <td :rowspan="getFormatsLength">
          <div class="m-auto row text-center">
            <div class="col-sm"/>
            <div class="col-auto text-right">{{ $t('document.sheets') }}</div>
            <plus-icon
                v-if="isEdit"
                class="col-1"
                fill-color="green"
                @click="addFormat"
            />
            <div v-else-if="!isEdit" class="col-1"/>

            <div class="col-sm"/>
          </div>
        </td>
        <td colspan="2">
          <div
              class="row border-bottom m-0 p-0"
              v-for="ws of currentDocument.documentFormatDto "
              :key="ws.id"
          >
            <div class="col-sm my-2"> {{ ws.formatDto.name }}</div>
            <div class="col-sm my-2"> {{ ws.qty }}</div>
            <minus-icon
                class="col-1  my-2"
                fill-color="red"
                v-if="isEdit"
                @click="removeFormat(ws)"
            />
            <div v-else-if="!isEdit" class="col-1"/>

          </div>

        </td>
      </tr>
      </tbody>
    </table>

    <div class="row m-auto w-75" v-if="isEdit && !isDelete">
      <b-button class="col-3 m-auto mt-xl-5 bg-light btn btn-outline-success text-success " @click="saveDocument">
        <h5 class="m-auto">
          {{ $t('message.button.save') }}
        </h5>
      </b-button>
      <div class="col-3"/>
      <b-button v-if="isCreate" class="col-3 m-auto mt-xl-5" @click="goBack">
        <h5 class="m-auto">
          {{ $t('message.button.back') }}
        </h5>
      </b-button>
      <b-button v-else-if="!isCreate" class="col-3 m-auto mt-xl-5" @click="cancel">
        <h5 class="m-auto">
          {{ $t('message.button.cancel') }}
        </h5>
      </b-button>
    </div>
    <div class="row m-auto w-75" v-else-if="!isEdit && isDelete">
      <b-button class="col-3 m-auto mt-xl-5 bg-light btn btn-outline-danger text-danger " @click="deleteDocument">
        <h5 class="m-auto">
          {{ $t('message.button.confirm_delete') }}
        </h5>
      </b-button>
      <div class="col-3"/>

      <b-button class="col-3 m-auto mt-xl-5" @click="cancel">
        <h5 class="m-auto">
          {{ $t('message.button.cancel') }}
        </h5>
      </b-button>
    </div>
    <div class="row m-auto w-75" v-else>
      <b-button class="col-3 m-auto mt-xl-5" @click="goBack">
        <h5 class="m-auto">
          {{ $t('message.button.back') }}
        </h5>
      </b-button>
      <b-button class="col-3 m-auto mt-xl-5 bg-light btn btn-outline-warning text-warning " @click="editDocument">
        <h5 class="m-auto">
          {{ $t('message.button.edit') }}
        </h5>
      </b-button>
      <b-button class="col-3 m-auto mt-xl-5 bg-light btn btn-outline-danger text-danger "
                @click="attemptDeleteDocument">
        <h5 class="m-auto">
          {{ $t('message.button.delete') }}
        </h5>
      </b-button>
    </div>
  </div>
  <div v-else class="row">
    <h5>! DOCUMENT NOT FOUND</h5>
    <b-button class="col-3 m-auto mt-xl-5" @click="goBack">
      <h5 class="m-auto">
        {{ $t('message.button.back') }}
      </h5>
    </b-button>

  </div>

</template>

<script>
  import {mapActions, mapGetters} from "vuex";
  import router from "@/router";
  import DocumentFormatModal from "@/components/document/DocumentFormatModal";
  import DocumentProjectModal from "@/components/document/DocumentProjectModal";
  import BasicModal from "../modals/BasicModal";
  import RolePermissionsModalFooter from "@/components/role/RolePermissionsModalFooter";
  import DocumentProjectModalFooter from "@/components/document/DocumentProjectModalFooter";

  export default {
    name: "Document",
    components: {
      DocumentProjectModalFooter,
      RolePermissionsModalFooter, DocumentFormatModal,
      DocumentProjectModal,
      BasicModal,
    },
    data() {
      return {
        isAddProjectCreate: false,
      isAddProjectEdit: false,
      isAddProjectShow: false,
      isEdit: false,
      isDelete: false,
      isCreate: false,
      id: 0,
      currentDocument: {},
      term: '',
    }
  },
  props: {
    document: {
      type: Object,
      default: () => {
        return {}
      }
    }
  },
  computed: {
    ...mapGetters([
      'DOCUMENT',
      'FOUND_ENGINEERS',
      'ENGINEERS',
      'FORMATS',
      'PROJECTS',

      'MODAL_SHOW',
      'MODAL_EDIT',
      'MODAL_CREATE',
    ]),
    getDocumentModalTitle() {
      return this.currentDocument.designation
    },
    getFormatsLength() {
      if (this.DOCUMENT.documentFormatDto !== undefined && this.DOCUMENT.documentFormatDto.length > 0) {
        return this.DOCUMENT.documentFormatDto.length;
      }
      return 0;
    },
    isSigned() {
      return this.currentDocument.documentApprovalsDto !== undefined &&
          this.currentDocument.documentApprovalsDto.designerId !== undefined &&
          this.currentDocument.documentApprovalsDto.designerId !== ''
    },
    getDesignerName: {
      get() {
        return this.currentDocument.documentApprovalsDto.designerName
      },
      set(newValue) {
        this.currentDocument.documentApprovalsDto.designerName = newValue;
      }
    },
    getDocFormats() {
      let ids = [];
      for (let i = 0; i < this.FORMATS.length; i++) {
        let isExist = false;
        for (let j = 0; j < this.currentDocument.documentFormatDto.length; j++) {
          if (this.FORMATS[i].name === this.currentDocument.documentFormatDto[j].formatDto.name) {
            isExist = true;
          }
        }
        if (!isExist) {
          ids.push(this.FORMATS[i]);
        }
      }
      return ids;
    },
    getProjects() {
      return this.currentDocument.projects
    }


  },
  methods: {
    ...mapActions([
      'GET_DOCUMENT',
      'DELETE_DOCUMENT',
      'GET_ALL_DOCUMENTS',
      'CREATE_DOCUMENT',
      'CREATE_SIGNED_DOCUMENT',
      'UPDATE_DOCUMENT',
      'UPDATE_SIGNED_DOCUMENT',
      'GET_ALL_ENGINEERS_LAST_NAME_START',
      'GET_ALL_ENGINEERS',
      'GET_ALL_FORMATS',
      'GET_ALL_PROJECTS',
      'ADD_PROJECT_IN_DOCUMENT',
      'REMOVE_PROJECT_IN_DOCUMENT',

      'SET_MODAL_STATE'
    ]),
    goBack() {
      router.go(-1)
    },
    editDocument() {
      this.isEdit = true;
    },
    saveDocument() {
      if (this.isCreate) {
        if (this.isSigned) {
          console.log('create signed ' + this.currentDocument.documentApprovalsDto.designerId);
          this.CREATE_SIGNED_DOCUMENT(this.currentDocument).then(
              () => {
                router.push('/planner/documents')
              }
          );
        } else {
          console.log('create unsigned' + this.currentDocument.documentApprovalsDto.designerId);
          this.CREATE_DOCUMENT(this.currentDocument).then(
              () => {
                router.push('/planner/documents')
              }
          );
        }

      } else {
        if (this.isSigned) {
          this.UPDATE_SIGNED_DOCUMENT(this.currentDocument).then(
              () => {
                router.push('/planner/documents')
              }
          );
        } else {
          this.UPDATE_DOCUMENT(this.currentDocument).then(
              () => {
                router.push('/planner/documents')
              }
          );
        }
      }
      this.isEdit = false;
      this.isCreate = false;
    },
    attemptDeleteDocument() {
      this.isDelete = true;
    },
    deleteDocument() {
      this.isDelete = false;
      this.DELETE_DOCUMENT(this.id).then(
          () => {
            router.push('/planner/documents')
          }
      );
    },
    cancel() {
      this.isEdit = false;
      this.isDelete = false;
      this.currentDocument = this.DOCUMENT;
    },
    addFormat() {
      this.showModal();
    },
    saveAddFormat() {
      this.closeModal();
      this.$refs.documentAddFormat.returnItem();
    },
    returnItem(item) {
      if (this.currentDocument.documentFormatDto === undefined) {
        this.currentDocument.documentFormatDto = [];
      }
      this.currentDocument.documentFormatDto.push(item);
    },
    removeFormat(value) {
      if (this.currentDocument.documentFormatDto !== undefined && this.currentDocument.documentFormatDto.length > 0) {
        const ind = this.currentDocument.documentFormatDto.indexOf(value);
        this.currentDocument.documentFormatDto.splice(ind, 1);
      }
    },
    showModal() {
      this.SET_MODAL_STATE(true);
    },
    closeModal() {
      this.SET_MODAL_STATE(false);
      this.isAddProjectShow = false;
      this.isAddProjectEdit = false;
      this.isAddProjectCreate = false;
    },
    returnProject(item) {

      let dao = {};
      dao.document = this.currentDocument.id;
      dao.project = item.id
      // this.ADD_PROJECT_IN_DOCUMENT(dao);

      if (this.currentDocument.projects === undefined) {
        this.currentDocument.projects = [];
      }
      this.currentDocument.projects.push(item);
      this.isAddProjectShow = false;
      this.isAddProjectEdit = false;
      this.isAddProjectCreate = false;
      // this.isEdit = false;
    },
    addProject() {
      this.isAddProjectShow = true;
    },
    saveProject() {
      this.$refs.documentAddProject.returnItem();
    },
    removeProject(value) {
      let dao = {};
      dao.document = this.currentDocument.id;
      dao.project = value.id
      // this.REMOVE_PROJECT_IN_DOCUMENT(dao);
      if (this.currentDocument.projects !== undefined && this.currentDocument.projects.length > 0) {
        const ind = this.currentDocument.projects.indexOf(value);
        this.currentDocument.projects.splice(ind, 1);
      }

    },
  },
  mounted() {
    this.SET_MODAL_STATE(false);
    this.id = this.$route.params.id;
    if (this.id == 0) {
      this.isCreate = true;
      this.isEdit = true;
      this.currentDocument = {};
      this.currentDocument.documentApprovalsDto = {};
      this.currentDocument.documentFormatDto = [];

    } else {
      this.GET_DOCUMENT(this.id).then(() =>
          this.currentDocument = this.DOCUMENT
      );
    }
    this.GET_ALL_ENGINEERS();
    this.GET_ALL_FORMATS();
    this.GET_ALL_PROJECTS();
  }
}
</script>

<style scoped>

</style>