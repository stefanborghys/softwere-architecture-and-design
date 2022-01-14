# Plant UML

## Installation

### Graphviz (dot)

Plant UML needs graphviz-dot to generate some diagrams.

Plant UML installation: https://plantuml.com/graphviz-dot

#### Mac OS

1. Install `libtool` (if needed)  

> GNU Libtool is a generic library support script that hides the complexity of using shared libraries behind a consistent, portable interface.  
>   
> https://formulae.brew.sh/formula/libtool  
> https://www.gnu.org/software/libtool/  

```
brew install libtool
brew link libtool
```

2. Install `graphviz`

```
brew install graphviz
brew link --overwrite graphviz
```

3. Set the `GRAPHVIZ_DOT` environment variable

Add following line to `.bash_profile`  
E.g. `export GRAPHVIZ_DOT=/usr/local/Cellar/graphviz/2.50.0/bin/dot`

> Note! the graphviz version number might be different