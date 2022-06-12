package com.cts.training.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Loan Application Entity Class
 */
@Entity
@Table(name = "loanapplication")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Model Class for Customer Loan Application details")
public class Loan_Application  {

	@Id
	@ApiModelProperty(value = "Application Id of the Customer")
	private Integer applicatonId;

	@ApiModelProperty(value = "Customer Id of the Loan Bearer")
	private Integer customerId;

	@ApiModelProperty(value = "Loan Amount for the Loan")
	private Double loanamount;

	@ApiModelProperty(value = "Tenure for the Loan")
	private Integer tenure;

	@ApiModelProperty(value = "Collateral Id for the Loan")
	private Integer collateralId;
	
	@ApiModelProperty(value = "Type of the Collateral")
	private String collateralType;
	
	@ApiModelProperty(value = "Status of the Loan Application")
	private String status;

}

