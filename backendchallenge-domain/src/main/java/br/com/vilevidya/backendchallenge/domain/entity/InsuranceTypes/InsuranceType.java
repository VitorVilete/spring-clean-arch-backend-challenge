package br.com.vilevidya.backendchallenge.domain.entity.InsuranceTypes;

import java.math.BigDecimal;

public record InsuranceType(String name, Number iofTaxValue, Number pisTaxValue, Number cofinsTaxValue) {
    //Builder
    public static class InsuranceTypeBuilder{
        private String name;

        private Number iofTaxValue;

        private Number pisTaxValue;

        private Number cofinsTaxValue;
        public InsuranceTypeBuilder(){
        }

        public InsuranceTypeBuilder(String name, Number iofTaxValue, Number pisTaxValue, Number cofinsTaxValue){
            this.name = name;
            this.iofTaxValue = iofTaxValue;
            this.pisTaxValue = pisTaxValue;
            this.cofinsTaxValue = cofinsTaxValue;
        }
        public InsuranceTypeBuilder setName(String name){
            this.name = name;
            return this;
        }
        public InsuranceTypeBuilder setIofTaxValue(Number iofTaxValue){
            this.iofTaxValue = iofTaxValue;
            return this;
        }
        public InsuranceTypeBuilder setPisTaxValue(Number pisTaxValue){
            this.pisTaxValue = pisTaxValue;
            return this;
        }
        public InsuranceTypeBuilder setCofinsTaxValue(Number cofinsTaxValue){
            this.cofinsTaxValue = cofinsTaxValue;
            return this;
        }
        public InsuranceType build(){
            return new InsuranceType(name, iofTaxValue, pisTaxValue, cofinsTaxValue);
        }
    }
}
