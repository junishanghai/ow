// net.vtst.ow.eclipse.less: An Eclipse module for LESS (http://lesscss.org)
// (c) Vincent Simonet, 2011.  All rights reserved.

package net.vtst.ow.eclipse.less.ui.syntaxcoloring;

import net.vtst.eclipse.easyxtext.ui.syntaxcoloring.EasySemanticHighlightingCalculator;
import net.vtst.ow.eclipse.less.less.Declaration;
import net.vtst.ow.eclipse.less.less.MediaExpression;
import net.vtst.ow.eclipse.less.less.MediaQuery;
import net.vtst.ow.eclipse.less.less.MixinDefinition;
import net.vtst.ow.eclipse.less.less.MixinDefinitionGuard;
import net.vtst.ow.eclipse.less.less.MixinDefinitionGuards;
import net.vtst.ow.eclipse.less.less.MixinParameter;
import net.vtst.ow.eclipse.less.less.NumericLiteral;
import net.vtst.ow.eclipse.less.less.VariableDefinition;
import net.vtst.ow.eclipse.less.services.LessGrammarAccess;

import com.google.inject.Inject;

public class LessSemanticHighlightingCalculator extends EasySemanticHighlightingCalculator {
  
  @Inject
  private LessGrammarAccess grammar;

  @Inject
  protected LessHighlightingConfiguration highlightingConfig;

  @Override
  protected void configure() {
    bindRule(grammar.getInnerSelectorRule(), highlightingConfig.SELECTOR);
    bindRule(grammar.getToplevelSelectorRule(), highlightingConfig.SELECTOR);
    bindRule(grammar.getHashOrClassRule(), MixinDefinition.class, highlightingConfig.SELECTOR);
    bindRule(grammar.getVariableSelectorRule(), MixinDefinition.class, highlightingConfig.SELECTOR);
    bindRule(grammar.getHashOrClassCrossReferenceRule(), highlightingConfig.MIXIN_CALL);
    bindRule(grammar.getHashOrClassCrossReferenceRule(), highlightingConfig.MIXIN_CALL);
    bindRule(grammar.getPropertyRule(), highlightingConfig.PROPERTY);
    bindRule(grammar.getVariableDefinitionLhsRule(), highlightingConfig.VARIABLE_DEFINITION);
    bindRule(grammar.getNumericLiteralRule(), NumericLiteral.class, highlightingConfig.NUMERIC_LITERAL);
    bindKeyword(":", Declaration.class, highlightingConfig.PROPERTY);
    bindRule(grammar.getMediaFeatureRule(), highlightingConfig.MEDIA_FEATURE);
    bindKeyword(":", MediaExpression.class, highlightingConfig.MEDIA_FEATURE);
    bindKeyword("not", MediaQuery.class, highlightingConfig.PROPERTY);
    // TODO Is this useful once the mixin definitions are correctly colored?
    bindKeyword("when", MixinDefinitionGuards.class, highlightingConfig.SELECTOR);
    bindKeyword("not", MixinDefinitionGuard.class, highlightingConfig.SELECTOR);
  }

}
