<template>
  <div class="w-100">
    <table class="table table-borderless">
      <thead class="thead-light">
      <tr>
        <td class="col-2">
          <h5>{{ $t("message.overview.year") }}</h5>
        </td>
        <td class="col-2">
          <h5>{{ $t("message.overview.month") }}</h5>
        </td>
        <td class="col-2">
          <h5>{{ $t("message.overview.department") }}</h5>
        </td>
      </tr>
      <tr>
        <td>
          <b-form-select
              v-model="currentYear"
              :options="years"
              value-field="value"
              text-field="value"
              disabled-field="notEnabled"
              @change="searchItem"
          >
            <b-form-select-option :value="null">{{ $t("message.overview.all_years") }}</b-form-select-option>
          </b-form-select>
        </td>
        <td>
          <b-form-select
              v-model="currentMonth"
              :options="ALL_LOCALE_MONTHS"
              value-field="value"
              text-field="name"
              disabled-field="notEnabled"
              @change="searchItem"
          />
        </td>
        <td>
          <b-form-select
              v-model="currentDepartment.id"
              :options="departments"
              value-field="id"
              text-field="name"
              disabled-field="notEnabled"
              @change="searchItem"
          >
            <b-form-select-option :value="null">Вся контора</b-form-select-option>
          </b-form-select>
        </td>
      </tr>
      </thead>
    </table>

  </div>
</template>

<script>
import {mapActions, mapGetters} from "vuex";

export default {
  name: "PlanSelectors",
  data() {
    return {
      currentYear: 2021,
      currentMonth: 12,
      currentDepartment: {
        id: 1,
        name: 'УПиР'
      },
    }
  },
  props: {
    years: {
      type: Array,
      default:
          () => {
            return []
          }
    },
    months: {
      type: Array,
      default:
          () => {
            return []
          }
    },
    departments: {
      type: Array,
      default:
          () => {
            return []
          }
    },
  },
  computed: {
    ...mapGetters([
      'ALL_LOCALE_MONTHS',
    ]),
  },
  methods: {
    ...mapActions([
      'GET_ALL_PLANS',
    ]),
    searchItem() {
      let item = {
        "year": this.currentYear,
        "month": this.currentMonth,
        "depId": this.currentDepartment.id,
      };
      this.GET_ALL_PLANS(item);
    },

  }
}
</script>

<style scoped>

</style>