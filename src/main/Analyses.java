package main;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javafx.util.Pair;

import com.flowjo.lib.parameters.ParameterSetInterface;

/**
 * Created by Isaac on 7/11/2017.
 */
class Analyses {
    private static Analyses singleton = null;
    private static List<AnalysisMember> analyses;
    private static List<Pair<String, List<String>>> all_gene_sets;
    private AnalysisMember current_analysis;

    private Analyses(Collection<ParameterSetInterface> allParams) {
        analyses = new ArrayList<>();
        all_gene_sets = new ArrayList<>();
        current_analysis = null;
        initializeGeneSets(allParams);
    }

    static Analyses getInstance(Collection<ParameterSetInterface> allGenes) {
        if (singleton == null)
            singleton = new Analyses(allGenes);

        return singleton;
    }

    int getCount() {return analyses.size();}
    List<AnalysisMember> getAnalyses() {return analyses;}
    AnalysisMember getCurrentAnalysis() {return current_analysis;}
    List<Pair<String, List<String>>> getAllGeneSets() {return all_gene_sets;}
    String getCurrentAnalysisName() {return (current_analysis != null) ? current_analysis.getAnalysisName() : null;}

    void addAnalysis(AnalysisMember member) {
        analyses.add(member);
        current_analysis = member;
    }
    void addGeneSet(Pair<String, List<String>> pr) {all_gene_sets.add(pr);}

    boolean renameSelectedAnalysis(String newAnalysisName) {
        for (AnalysisMember mem : analyses) {
            if (mem.getAnalysisName().equals(getCurrentAnalysisName())) {
                mem.setAnalysisName(newAnalysisName);
                return true;
            }
        }
        return false;
    }

    AnalysisMember setCurrentAnalysis(String analysis_name) {
        for(AnalysisMember analysis: analyses) {
            if(analysis_name.equals(analysis.getAnalysisName())) {
                current_analysis = analysis;
                return analysis;
            }
        }
        return null;
    }

    boolean doesExist(AnalysisMember member) {
        for(AnalysisMember analysis: analyses)
            if(member.equals(analysis))
                return true;

        return false;
    }
    boolean removeAnalysis(String analysis_name) {
        for(AnalysisMember mem : analyses) {
            if(mem.getAnalysisName().equals(analysis_name)) {
                analyses.remove(mem);
                return true;
            }
        }
        return false;
    }

    void clear() { analyses.clear(); }

    private void initializeGeneSets(Collection<ParameterSetInterface> allGenes) {

        //ensure program is in it's first run
        if(this.getCount() == 0) {
            for (ParameterSetInterface set : allGenes) {
                if (!set.getName().equals("All"))
                    this.addGeneSet(new Pair<>(set.getName(), set.getParameterNames()));
            }
        }
    }
}
