package main.java.com.cisco.dft.cimv.model;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class metricsInfo {
	
	private String _id;
	private String parent_id;
	private long lines;
	private long lines_of_code;
	private String statements;
	private long files;
	private long classes;
	private long packages;
	private long functions;
	private String accessors;
	private String technical_debt;
	private String rules_compliance;
	private String issues;
	private int blocker_issues;
	private int critical_issues;
	private String major_issues;
	private String comments;
	private long comment_lines;
	private String blank_comments;
	private long commented_out_loc;
	private String duplication;
	private String duplicated_lines;
	private String complexity;
	private String complexity_per_file;
	private String complexity_per_class;
	private String complexity_per_function;
	private String lines_to_cover;
	private String uncovered_lines;
	private String conditions_to_cover;
	private String uncovered_conditions;
	private String unit_test_coverage;
	private String line_coverage;
	private String branch_coverage;
	private String unit_test_success;
	private String no_of_tests;
	private String unit_test_errors;
	private String unit_test_failures;
	private String total_imp_issues;
	private float quality_index;

	private String weighted_issues;
	private String last_build_timestamp;
	private String svn_url;
	private String sonar_key;
	private String last_svn_checkin_timestamp;
	private String last_sonar_code_analysis_timestamp;
	private String weighted_average;
	private String project_type;
	
	private float coding_qi;
	private float coverage_qi;
	private float complexity_qi;
	private float duplicates_qi;
	private float comments_qi;
	private String release;
	
	public static String roundMetric(String num){
		double numTemp = Double.parseDouble(num);
		return String.valueOf(Math.round(numTemp*100)/100.0);
	}
	
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getParent_id() {
		return parent_id;
	}
	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}
	public long getLines() {
		return lines;
	}
	public void setLines(long lines) {
		this.lines = lines;
	}
	public long getLines_of_code() {
		return lines_of_code;
	}
	public void setLines_of_code(long lines_of_code) {
		this.lines_of_code = lines_of_code;
	}
	public String getStatements() {
		return statements;
	}
	public void setStatements(String statements) {
		this.statements = statements;
	}
	public long getFiles() {
		return files;
	}
	public void setFiles(long files) {
		this.files = files;
	}
	public long getClasses() {
		return classes;
	}
	public void setClasses(long classes) {
		this.classes = classes;
	}
	public long getPackages() {
		return packages;
	}
	public void setPackages(long packages) {
		this.packages = packages;
	}
	public long getFunctions() {
		return functions;
	}
	public void setFunctions(long functions) {
		this.functions = functions;
	}
	public String getAccessors() {
		return accessors;
	}
	public void setAccessors(String accessors) {
		this.accessors = accessors;
	}
	public String getTechnical_debt() {
		return technical_debt;
	}
	public void setTechnical_debt(String technical_debt) {
		this.technical_debt = technical_debt;
	}
	public String getIssues() {
		return issues;
	}
	public void setIssues(String issues) {
		this.issues = issues;
	}
	public int getBlocker_issues() {
		return blocker_issues;
	}
	public void setBlocker_issues(int blocker_issues) {
		this.blocker_issues = blocker_issues;
	}
	public int getCritical_issues() {
		return critical_issues;
	}
	public void setCritical_issues(int critical_issues) {
		this.critical_issues = critical_issues;
	}
	public String getMajor_issues() {
		return major_issues;
	}
	public void setMajor_issues(String major_issues) {
		this.major_issues = major_issues;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = roundMetric(comments)+"%";
	}
	public long getComment_lines() {
		return comment_lines;
	}
	public void setComment_lines(long comment_lines) {
		this.comment_lines = comment_lines;
	}
	public String getBlank_comments() {
		return blank_comments;
	}
	public void setBlank_comments(String blank_comments) {
		this.blank_comments = blank_comments;
	}
	public long getCommented_out_loc() {
		return commented_out_loc;
	}
	public void setCommented_out_loc(long commented_out_loc) {
		this.commented_out_loc = commented_out_loc;
	}
	public String getDuplication() {
		return duplication;
	}
	public void setDuplication(String duplication) {
		this.duplication = roundMetric(duplication)+"%";
	}
	public String getDuplicated_lines() {
		return duplicated_lines;
	}
	public void setDuplicated_lines(String duplicated_lines) {
		this.duplicated_lines = roundMetric(duplicated_lines);
	}
	public String getComplexity() {
		return complexity;
	}
	public void setComplexity(String complexity) {
		this.complexity = roundMetric(complexity);
	}
	public String getComplexity_per_file() {
		return complexity_per_file;
	}
	public void setComplexity_per_file(String complexity_per_file) {
		this.complexity_per_file = roundMetric(complexity_per_file);
	}
	public String getComplexity_per_class() {
		return complexity_per_class;
	}
	public void setComplexity_per_class(String complexity_per_class) {
		this.complexity_per_class = roundMetric(complexity_per_class);
	}
	public String getComplexity_per_function() {
		return complexity_per_function;
	}
	public void setComplexity_per_function(String complexity_per_function) {
		this.complexity_per_function = roundMetric(complexity_per_function);
	}
	public String getLines_to_cover() {
		return lines_to_cover;
	}
	public void setLines_to_cover(String lines_to_cover) {
		this.lines_to_cover = lines_to_cover;
	}
	public String getUncovered_lines() {
		return uncovered_lines;
	}
	public void setUncovered_lines(String uncovered_lines) {
		this.uncovered_lines = uncovered_lines;
	}
	public String getConditions_to_cover() {
		return conditions_to_cover;
	}
	public void setConditions_to_cover(String conditions_to_cover) {
		this.conditions_to_cover = conditions_to_cover;
	}
	public String getUncovered_conditions() {
		return uncovered_conditions;
	}
	public void setUncovered_conditions(String uncovered_conditions) {
		this.uncovered_conditions = uncovered_conditions;
	}
	public String getUnit_test_coverage() {
		return unit_test_coverage;
	}
	public void setUnit_test_coverage(String unit_test_coverage) {
		this.unit_test_coverage = roundMetric(unit_test_coverage);
	}
	public String getLine_coverage() {
		return line_coverage;
	}
	public void setLine_coverage(String line_coverage) {
		this.line_coverage = roundMetric(line_coverage);
	}
	public String getBranch_coverage() {
		return branch_coverage;
	}
	public void setBranch_coverage(String branch_coverage) {
		this.branch_coverage = roundMetric(branch_coverage);
	}
	public String getUnit_test_success() {
		return unit_test_success;
	}
	public void setUnit_test_success(String unit_test_success) {
		this.unit_test_success = unit_test_success;
	}
	public String getNo_of_tests() {
		return no_of_tests;
	}
	public void setNo_of_tests(String no_of_tests) {
		this.no_of_tests = no_of_tests;
	}
	public String getUnit_test_errors() {
		return unit_test_errors;
	}
	public void setUnit_test_errors(String unit_test_errors) {
		this.unit_test_errors = unit_test_errors;
	}
	public String getUnit_test_failures() {
		return unit_test_failures;
	}
	public void setUnit_test_failures(String unit_test_failures) {
		this.unit_test_failures = unit_test_failures;
	}
	public String getTotal_imp_issues() {
		return total_imp_issues;
	}
	public void setTotal_imp_issues(String total_imp_issues) {
		this.total_imp_issues = roundMetric(total_imp_issues);
	}
	public float getQuality_index() {
		return quality_index;
	}
	public void setQuality_index(float quality_index) {
		this.quality_index = (float) (Math.round(quality_index * 100)/100.0);
	}

	public String getRules_compliance() {
		return rules_compliance;
	}

	public void setRules_compliance(String rules_compliance) {
		this.rules_compliance = roundMetric(rules_compliance)+"%";
	}

	public String getWeighted_issues() {
		return weighted_issues;
	}

	public void setWeighted_issues(String weighted_issues) {
		this.weighted_issues = weighted_issues;
	}

	public String getLast_build_timestamp() {
		return last_build_timestamp;
	}

	public void setLast_build_timestamp(String last_build_timestamp) {
		this.last_build_timestamp = last_build_timestamp;
	}

	public String getSvn_url() {
		return svn_url;
	}

	public void setSvn_url(String svn_url) {
		this.svn_url = svn_url;
	}

	public String getSonar_key() {
		return sonar_key;
	}

	public void setSonar_key(String sonar_key) {
		this.sonar_key = sonar_key;
	}

	public String getLast_svn_checkin_timestamp() {
		return last_svn_checkin_timestamp;
	}

	public void setLast_svn_checkin_timestamp(String last_svn_checkin_timestamp) {
		this.last_svn_checkin_timestamp = last_svn_checkin_timestamp;
	}

	public String getLast_sonar_code_analysis_timestamp() {
		return last_sonar_code_analysis_timestamp;
	}

	public void setLast_sonar_code_analysis_timestamp(
			String last_sonar_code_analysis_timestamp) {
		this.last_sonar_code_analysis_timestamp = last_sonar_code_analysis_timestamp;
	}

	public String getWeighted_average() {
		return weighted_average;
	}

	public void setWeighted_average(String weighted_average) {
		this.weighted_average = roundMetric(weighted_average);
	}

	public String getProject_type() {
		return project_type;
	}

	public void setProject_type(String project_type) {
		this.project_type = project_type;
	}

	public float getCoding_qi() {
		return coding_qi;
	}

	public void setCoding_qi(float coding_qi) {
		this.coding_qi = 1 - (Math.round(coding_qi*100)/100);
	}

	public float getCoverage_qi() {
		return coverage_qi;
	}

	public void setCoverage_qi(float coverage_qi) {
		this.coverage_qi = 1 - (Math.round(coverage_qi*100)/100);
	}

	public float getComplexity_qi() {
		return complexity_qi;
	}

	public void setComplexity_qi(float complexity_qi) {
		this.complexity_qi = 1 - (Math.round(complexity_qi*100)/100);
	}

	public float getDuplicates_qi() {
		return duplicates_qi;
	}

	public void setDuplicates_qi(float duplicates_qi) {
		this.duplicates_qi = 1 - (Math.round(duplicates_qi*100)/100);
	}

	public float getComments_qi() {
		return comments_qi;
	}

	public void setComments_qi(float comments_qi) {
		this.comments_qi = 1 - (Math.round(comments_qi*100)/100);
	}
	public String getRelease() {
		return release;
	}
	public void setRelease(String release) {
		this.release = release;
	}
}
