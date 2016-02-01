package net.ahri.burpconfig.params;

import net.ahri.burpconfig.KvpWriter;

import java.util.ArrayList;
import java.util.List;

public class ScopeExcludeConsumer implements Params.Consumer
{
    private final List<Scope> scopes = new ArrayList<Scope>();
    private final KvpWriter scopeWriter;

    public ScopeExcludeConsumer(KvpWriter scopeWriter)
    {
        this.scopeWriter = scopeWriter;
    }

    @Override
    public int consume(int i, String[] args)
    {
        if (Params.isOutOfBounds(i, args))
        {
            throw new Params.ParamException("expected scope definition");
        }

        scopes.add(new Scope(args[i]));

        return 1;
    }

    @Override
    public String getDescription()
    {
        return "add a target exclude scope, can be called multiple times";
    }

    public void writeScopes()
    {
        for (int i = 0; i < scopes.size(); i++)
        {
            scopeWriter.write("target.scopeexclude" + i, scopes.get(i).toString());
        }
    }
}
