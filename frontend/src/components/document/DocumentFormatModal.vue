<template>
  <div class="nic-popup-body container-fluid">
    <div role="group" class="row m-0 mt-1 mx-2">
      <div class="col-4 m-auto mr-2 ">
        {{ $t('document.sheets') }}:
      </div>
      <b-form-select
          v-model="item.name"
          :options="engineers"
          class="col-sm text-secondary border rounded-lg"
          value-field="name"
          text-field="name"
          disabled-field="notEnabled"
      />
    </div>
    <div role="group" class="row m-0 mt-1 mx-2">
      <div class="col-4 m-auto mr-2 ">
        {{ $t('document.sheet_qty') }}:
      </div>
      <b-form-input
          class="col-sm text-secondary border rounded-lg m-0"
          :value="qty"
          v-model="qty"
          trim
      />
    </div>
  </div>
</template>

<script>

export default {
  name: "DocumentFormatModal",
  components: {},
  data() {
    return {
      itemUser: {...this.item},
      qty: 0
    }
  },
  props: {
    item: {
      type: Object,
      default: () => {
        return {}
      }
    },
    engineers: {
      type: Array,
      default() {
        return []
      }
    },

    roles: [],
    departments: [],
    statuses: [],
    isEdit: {
      type: Boolean,
      default: false
    },
    error: {
      type: Object,
      default: () => {
        return {}
      }
    }
  },
  computed: {
    getSubmitEnabled() {
      return !this.getErrorInputLogin();
    },

    getErrorInputLogin() {
      if (this.itemUser.login !== undefined) {
        if (this.itemUser.login.length < 3) {
          return false;
        }
        return true;
      }
      return false;
    },
    getErrorLogin() {
      return this.error.login !== undefined ? 'border border-danger' : '';
    },
    getErrorFirstName() {
      return this.error.firstName !== undefined ? 'border border-danger' : '';
    },
    getErrorLastName() {
      return this.error.lastName !== undefined ? 'border border-danger' : '';
    },
    getErrorRole() {
      return (this.error.roleId !== undefined) ? 'border border-danger' : '';
    },
    getErrorDepartment() {
      return this.error.departmentId !== undefined ? 'border border-danger' : '';
    },
    getErrorPhone() {
      return this.error.contactDetails_phone !== undefined ? 'border border-danger' : '';
    },
    getErrorMobile() {
      return this.error.contactDetails_mobile !== undefined ? 'border border-danger' : '';
    },
    getErrorEmail() {
      return this.error.contactDetails_email !== undefined ? 'border border-danger' : '';
    },
    getErrorOffice() {
      return this.error.contactDetails_office !== undefined ? 'border border-danger' : '';
    },
  },
  methods: {
    returnItem() {
      const format = {
        'formatDto': {
          'name': this.item.name
        },
        'qty': this.qty
      }
      console.log(' item ' + format);
      this.$emit('returnItem', format);
    },
    isSubmitEnabled() {
      this.$emit('isSubmitEnabled', this.getSubmitEnabled);
    }
  }

}
</script>

<style scoped>
::placeholder {
  color: #b1b1b1;
}

</style>