/**
 * 新增表单验证
 */
$(document).ready(function() {
    window.reportcardFormValidator=$("#reportcardForm").bootstrapValidator({
        message: '无效的输入值',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            qcjsp_grade: {
                validators: {
                	numeric: {
                        message: '数值格式不正确~'
                    }
                }
            },
            qcjsp_score: {
                validators: {
                	numeric: {
                        message: '数值格式不正确~'
                    }
                }
            },
            zfp_grade: {
                validators: {
                	numeric: {
                        message: '数值格式不正确~'
                    }
                }
            },
            zfp_score: {
                validators: {
                	numeric: {
                        message: '数值格式不正确~'
                    }
                }
            },
            ldty_grade: {
                validators: {
                	numeric: {
                        message: '数值格式不正确~'
                    }
                }
            },
            ldty_score: {
                validators: {
                	numeric: {
                        message: '数值格式不正确~'
                    }
                }
            },
            ywqz_grade: {
                validators: {
                	numeric: {
                        message: '数值格式不正确~'
                    }
                }
            },
            ywqz_score: {
                validators: {
                	numeric: {
                        message: '数值格式不正确~'
                    }
                }
            },
            zpdjtmg_grade: {
                validators: {
                	numeric: {
                        message: '数值格式不正确~'
                    }
                }
            },
            zpdjtmg_score: {
                validators: {
                	numeric: {
                        message: '数值格式不正确~'
                    }
                }
            },
            syts_grade: {
                validators: {
                	numeric: {
                        message: '数值格式不正确~'
                    }
                }
            },
            syts_score: {
                validators: {
                	numeric: {
                        message: '数值格式不正确~'
                    }
                }
            },
            tqq_score: {
                validators: {
                	numeric: {
                        message: '数值格式不正确~'
                    }
                }
            },
            tqq_grade: {
                validators: {
                	numeric: {
                        message: '数值格式不正确~'
                    }
                }
            },
            tqsstscq_score: {
                validators: {
                	numeric: {
                        message: '数值格式不正确~'
                    }
                }
            },
            tqsstscq_grade: {
                validators: {
                	numeric: {
                        message: '数值格式不正确~'
                    }
                }
            },
            studentid: {
                validators: {
                	notEmpty: {
                        message: '编号不能为空~'
                    }
                }
            }
        }
    });
    $("#addSaveBtn").bind('click',function(e){
    	//验证
    	$(reportcardForm).data('bootstrapValidator').validate();
    	//获取验证是否通过
    	var isvalid=$(reportcardForm).data('bootstrapValidator').isValid();
    	if(!isvalid){
    		alert('表单信息格式不正确，请修改后再提交！');
    		return;
    	}
    	reportcardForm.action="save";
    	reportcardForm.submit();
    });
});
