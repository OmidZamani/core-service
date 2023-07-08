package com.boxi.ruleEngine.error;


@SuppressWarnings("unused")
public class RuleEvaluationException extends RuntimeException {

    private static final long serialVersionUID = -5231011305560978130L;

    public RuleEvaluationException() {
        super();
    }

    public RuleEvaluationException(String message, Throwable cause, boolean enableSuppression,
                                   boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public RuleEvaluationException(String message, Throwable cause) {
        super(message, cause);
    }

    public RuleEvaluationException(String message) {
        super(message);
    }

    public RuleEvaluationException(Throwable cause) {
        super(cause);
    }
}